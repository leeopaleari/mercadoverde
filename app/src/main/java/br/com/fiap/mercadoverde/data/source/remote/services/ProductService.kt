package br.com.fiap.mercadoverde.data.source.remote.services

import br.com.fiap.mercadoverde.data.source.remote.model.CategoryResponse
import br.com.fiap.mercadoverde.data.source.remote.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products")
    suspend fun getProductsByName(@Query("name") name: String): ProductResponse

    @GET("products")
    suspend fun getProductsByCategory(@Query("category") category: String): ProductResponse

    @GET("categories")
    suspend fun getCategories(): CategoryResponse
}
