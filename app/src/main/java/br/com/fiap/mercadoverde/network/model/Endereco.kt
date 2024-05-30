package br.com.fiap.mercadoverde.network.model

import br.com.fiap.mercadoverde.presentation.screens.auth.SignUp.state.SignUpUiState
import com.google.gson.annotations.SerializedName

data class Endereco(
    @SerializedName("cep")
    val zipCode: String = "",

    @SerializedName("logradouro")
    val street: String = "",

    @SerializedName("localidade")
    val city: String = "",
) {
    fun toSignUpScreenUiState() = SignUpUiState(
        street = street,
        city = city,
        country = "Brasil",

        isLoading = false,
        hasError = false
    )
}
