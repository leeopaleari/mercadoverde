package br.com.fiap.mercadoverde.presentation.screens.auth.SignIn.state

data class SignInUiState(
    val email: String = "",
    val password: String = "",

    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val onLoginClick: () -> Unit = {},

    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String? = null,
    val isLoginSuccess: Boolean = false
)