package br.com.fiap.mercadoverde.data.source.remote.services

import br.com.fiap.mercadoverde.data.source.remote.model.LoginRequest
import br.com.fiap.mercadoverde.data.source.remote.model.LoginResponse
import br.com.fiap.mercadoverde.data.source.remote.model.ProfileResponse
import br.com.fiap.mercadoverde.data.source.remote.model.RegisterRequest
import br.com.fiap.mercadoverde.data.source.remote.model.RegisterResponse
import br.com.fiap.mercadoverde.data.source.remote.model.UpdateProfileRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {
    @POST("users")
    suspend fun registerUser(@Body request: RegisterRequest): RegisterResponse

    @POST("login")
    suspend fun loginUser(@Body request: LoginRequest): LoginResponse

    @GET("users/profile")
    suspend fun getProfile(): ProfileResponse

    @PUT("users/profile")
    suspend fun updateProfile(@Header("Authorization") token: String, @Body request: UpdateProfileRequest): ProfileResponse
}
