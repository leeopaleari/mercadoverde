package br.com.fiap.mercadoverde.presentation.screens.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.mercadoverde.domain.services.SecureStorageService
import br.com.fiap.mercadoverde.data.source.remote.model.LoginRequest
import br.com.fiap.mercadoverde.data.source.remote.services.UserService
import br.com.fiap.mercadoverde.presentation.screens.auth.state.SignInUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userService: UserService,
    private val secureStorage: SecureStorageService
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.asStateFlow()

    private val _snackbarEvent = MutableSharedFlow<String>()
    val snackbarEvent: SharedFlow<String> = _snackbarEvent

    init {
        if (!secureStorage.getAccessToken().isNullOrEmpty()) {
            _uiState.value = _uiState.value.copy(
                hasError = false,
                isLoginSuccess = true
            )
        }
        _uiState.update { currentState ->
            currentState.copy(
                onEmailChange = { email ->
                    _uiState.value = _uiState.value.copy(email = email)
                },
                onPasswordChange = { password ->
                    _uiState.value = _uiState.value.copy(password = password)
                },
                onLoginClick = {
                    login()
                }
            )
        }
    }

    private fun login() {
        val email = _uiState.value.email
        val password = _uiState.value.password

        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
        viewModelScope.launch {

            try {
                val token = userService.loginUser(LoginRequest(email, password))

                secureStorage.saveAccessToken(token.accessToken)

                _uiState.value = _uiState.value.copy(
                    hasError = false,
                    isLoginSuccess = true
                )
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is HttpException -> {
                        when (e.code()) {
                            401 -> "Dados informados inválidos"
                            else -> "Falha no login, tente novamente"
                        }
                    }

                    else -> "Falha no login, tente novamente ${e.message}"
                }

                _uiState.value = _uiState.value.copy(
                    hasError = true,
                    isLoginSuccess = false
                )

                _snackbarEvent.emit(errorMessage)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}