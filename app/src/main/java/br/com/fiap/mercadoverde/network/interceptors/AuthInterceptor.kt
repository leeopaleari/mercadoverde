package br.com.fiap.mercadoverde.network.interceptors

import br.com.fiap.mercadoverde.domain.services.SecureStorageService
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val secureStorage: SecureStorageService
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = secureStorage.getAccessToken()
        val request = chain.request().newBuilder()

        accessToken?.let {
            request.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(request.build())
    }
}
