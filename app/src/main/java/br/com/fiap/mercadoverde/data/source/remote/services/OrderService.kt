package br.com.fiap.mercadoverde.data.source.remote.services

import br.com.fiap.mercadoverde.data.source.remote.model.CreateOrderRequest
import br.com.fiap.mercadoverde.data.source.remote.model.OrderResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderService {

    @POST("orders")
    suspend fun createOrder(@Body request: CreateOrderRequest): OrderResponse

    @GET("orders")
    suspend fun getOrders(@Header("Authorization") token: String): List<OrderResponse>

    @GET("orders/{id}")
    suspend fun getOrderById(@Header("Authorization") token: String, @Path("id") orderId: String): OrderResponse
}
