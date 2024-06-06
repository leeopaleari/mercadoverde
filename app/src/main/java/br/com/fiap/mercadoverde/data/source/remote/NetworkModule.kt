package br.com.fiap.mercadoverde.data.source.remote

import br.com.fiap.mercadoverde.domain.services.SecureStorageService
import br.com.fiap.mercadoverde.data.source.remote.interceptors.AuthInterceptor
import br.com.fiap.mercadoverde.data.source.remote.services.EnderecoService
import br.com.fiap.mercadoverde.data.source.remote.services.OrderService
import br.com.fiap.mercadoverde.data.source.remote.services.ProductService
import br.com.fiap.mercadoverde.data.source.remote.services.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://192.168.1.80:6868/api/v1/"
    private const val CEP_URL = "https://viacep.com.br/ws/"

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(secureStorage: SecureStorageService): AuthInterceptor {
        return AuthInterceptor(secureStorage)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    @Named("defaultRetrofit")
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

    @Singleton
    @Provides
    @Named("cepRetrofit")
    fun provideCepRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(CEP_URL)
        .build()

    @Provides
    @Singleton
    fun provideUserService(@Named("defaultRetrofit") retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductService(@Named("defaultRetrofit") retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    @Provides
    @Singleton
    fun provideOrderService(@Named("defaultRetrofit") retrofit: Retrofit): OrderService {
        return retrofit.create(OrderService::class.java)
    }

    @Provides
    @Singleton
    fun provideEnderecoService(@Named("cepRetrofit") retrofit: Retrofit): EnderecoService {
        return retrofit.create(EnderecoService::class.java)
    }
}
