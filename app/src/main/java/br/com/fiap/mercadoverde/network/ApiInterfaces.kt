package br.com.fiap.mercadoverde.network

data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String,
    val houseNumber: String,
    val street: String,
    val city: String,
    val country: String,
    val zipCode: String
)

data class RegisterResponse(val accessToken: String)

data class LoginRequest(val email: String, val password: String)

data class LoginResponse(val accessToken: String)

data class ProfileResponse(val name: String, val email: String, val street: String, val houseNumber: String, val city: String, val country: String, val zipCode: String)

data class UpdateProfileRequest(val name: String, val email: String, val street: String, val houseNumber: String, val city: String, val country: String, val zipCode: String)

data class CreateOrderRequest(val items: List<OrderItem>)

data class OrderItem(val productId: String, val quantity: Int)

data class OrderResponse(val id: String, val items: List<OrderItem>, val total: Double)

data class ProductResponse(val id: String, val name: String, val category: String, val price: Double)
