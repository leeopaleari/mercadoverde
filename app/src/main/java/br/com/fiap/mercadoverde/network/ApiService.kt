package br.com.fiap.mercadoverde.network

import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("users")
    suspend fun registerUser(@Body request: RegisterRequest): RegisterResponse

    @POST("login")
    suspend fun loginUser(@Body request: LoginRequest): LoginResponse

    @GET("users/profile")
    suspend fun getProfile(@Header("Authorization") token: String): ProfileResponse

    @PUT("users/profile")
    suspend fun updateProfile(@Header("Authorization") token: String, @Body request: UpdateProfileRequest): ProfileResponse

    @POST("orders")
    suspend fun createOrder(@Header("Authorization") token: String, @Body request: CreateOrderRequest): OrderResponse

    @GET("products")
    suspend fun getProductsByName(@Query("name") name: String): List<ProductResponse>
    @GET("products")
    suspend fun getProducts(): List<ProductResponse>

    @GET("orders")
    suspend fun getOrders(@Header("Authorization") token: String): List<OrderResponse>

    @GET("orders/{id}")
    suspend fun getOrderById(@Header("Authorization") token: String, @Path("id") orderId: String): OrderResponse
}
