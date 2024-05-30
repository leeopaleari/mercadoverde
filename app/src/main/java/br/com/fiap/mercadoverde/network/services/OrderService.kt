package br.com.fiap.mercadoverde.network.services

import br.com.fiap.mercadoverde.network.model.CreateOrderRequest
import br.com.fiap.mercadoverde.network.model.OrderResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderService {

    @POST("orders")
    suspend fun createOrder(@Header("Authorization") token: String, @Body request: CreateOrderRequest): OrderResponse

    @GET("orders")
    suspend fun getOrders(@Header("Authorization") token: String): List<OrderResponse>

    @GET("orders/{id}")
    suspend fun getOrderById(@Header("Authorization") token: String, @Path("id") orderId: String): OrderResponse
}
