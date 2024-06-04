package br.com.fiap.mercadoverde.network.services

import br.com.fiap.mercadoverde.network.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products")
    suspend fun getProductsByName(@Query("name") name: String): ProductResponse

}
