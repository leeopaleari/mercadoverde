package br.com.fiap.mercadoverde.data.di

import android.content.Context
import androidx.room.Room
import br.com.fiap.mercadoverde.data.repository.CartRepositoryImpl
import br.com.fiap.mercadoverde.data.repository.ProductRepositoryImpl
import br.com.fiap.mercadoverde.data.source.local.AppDatabase
import br.com.fiap.mercadoverde.data.source.local.cart.CartDao
import br.com.fiap.mercadoverde.data.source.local.product.ProductDao
import br.com.fiap.mercadoverde.domain.services.SecureStorageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Injection {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "CART_DB"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }

    @Provides
    @Singleton
    fun provideCartDao(appDatabase: AppDatabase): CartDao {
        return appDatabase.cartDao()
    }

    @Provides
    @Singleton
    fun provideCartRepository(cartDao: CartDao): CartRepositoryImpl {
        return CartRepositoryImpl(cartDao)
    }

    @Provides
    @Singleton
    fun provideProductRepository(productDao: ProductDao): ProductRepositoryImpl {
        return ProductRepositoryImpl(productDao)
    }

    @Singleton
    @Provides
    fun provideSecureStorage(@ApplicationContext context: Context): SecureStorageService {
        return SecureStorageService(context)
    }

}