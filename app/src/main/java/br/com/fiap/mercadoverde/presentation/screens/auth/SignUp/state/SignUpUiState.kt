package br.com.fiap.mercadoverde.presentation.screens.auth.SignUp.state

data class SignUpUiState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val zipCode: String = "",
    val street: String = "",
    val city: String = "",
    val country: String = "",
    val houseNumber: String = "",
    val canCreate: Boolean = false,
    val registerSuccess: Boolean = false,

    val onNameChange: (String) -> Unit = {},
    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val onZipCodeChange: (String) -> Unit = {},
    val onStreetChange: (String) -> Unit = {},
    val onCityChange: (String) -> Unit = {},
    val onCountryChange: (String) -> Unit = {},
    val onHouseNumberChange: (String) -> Unit = {},

    val isLoading: Boolean = false,
    val hasError: Boolean = false
) {
    fun canRegister(): Boolean {
        return fullName.isNotBlank() &&
            email.isNotBlank() &&
            password.isNotBlank() &&
            zipCode.isNotBlank() &&
            street.isNotBlank() &&
            city.isNotBlank() &&
            houseNumber.isNotBlank()
    }
}