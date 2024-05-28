package br.com.fiap.mercadoverde.presentation.screens.auth.SignUp.state

data class SignUpUiState  (
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val zipCode: String = "",
    val street: String = "",
    val city: String = "",
    val country: String = "",
    val houseNumber: String = "",
)