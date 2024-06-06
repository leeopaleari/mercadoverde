package br.com.fiap.mercadoverde.data.source.remote.model

import br.com.fiap.mercadoverde.presentation.screens.auth.state.SignUpUiState
import com.google.gson.annotations.SerializedName

data class Endereco(
    @SerializedName("cep")
    val zipCode: String = "",

    @SerializedName("bairro")
    val neighborhood: String = "",

    @SerializedName("uf")
    val state: String = "",

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
