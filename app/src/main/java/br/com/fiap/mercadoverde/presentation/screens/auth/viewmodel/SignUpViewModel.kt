package br.com.fiap.mercadoverde.presentation.screens.auth.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.mercadoverde.domain.services.SecureStorageService
import br.com.fiap.mercadoverde.data.source.remote.model.RegisterRequest
import br.com.fiap.mercadoverde.data.source.remote.services.EnderecoService
import br.com.fiap.mercadoverde.data.source.remote.services.UserService
import br.com.fiap.mercadoverde.presentation.screens.auth.state.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val cepService: EnderecoService,
    private val userService: UserService,
    private val secureStorage: SecureStorageService
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()

    private val _focusEvent = MutableSharedFlow<Unit>()
    val focusEvent: SharedFlow<Unit> = _focusEvent

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onPasswordChange = { password ->
                    _uiState.value = _uiState.value.copy(password = password)
                },
                onStateChange = { state ->
                    _uiState.value = _uiState.value.copy(state = state)
                },
                onNeighborhoodChange = { neighborhood ->
                    _uiState.value = _uiState.value.copy(neighborhood = neighborhood)
                },
                onEmailChange = { email ->
                    _uiState.value = _uiState.value.copy(email = email)
                },
                onNameChange = { fullName ->
                    _uiState.value = _uiState.value.copy(fullName = capitalizeName(fullName))
                },
                onZipCodeChange = { zipCode ->
                    _uiState.value = _uiState.value.copy(zipCode = zipCode)

                    if (zipCode.length == 8) {
                        viewModelScope.launch {
                            loadAddress(zipCode)
                        }
                    }
                },
                onStreetChange = { street ->
                    _uiState.value = _uiState.value.copy(street = street)
                },
                onCityChange = { city ->
                    _uiState.value = _uiState.value.copy(city = city)
                },
                onCountryChange = { country ->
                    _uiState.value = _uiState.value.copy(country = country)
                },
                onHouseNumberChange = { houseNumber ->
                    _uiState.value = _uiState.value.copy(houseNumber = houseNumber)
                }
            )
        }
    }

    fun createAccount() {
        val request = RegisterRequest(
            email = uiState.value.email,
            password = uiState.value.password,
            name = uiState.value.fullName,
            houseNumber = uiState.value.houseNumber,
            street = uiState.value.street,
            country = uiState.value.country,
            city = uiState.value.city,
            zipCode = uiState.value.zipCode,
            state = uiState.value.state,
            neighborhood = uiState.value.neighborhood
        )
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, hasError = false) }
            try {
                val token = userService.registerUser(request)

                secureStorage.saveAccessToken(token.accessToken)

                _uiState.update { it.copy(registerSuccess = true) }

            } catch (e: Exception) {
                Log.e("SignUpViewModel", "Error fetching address", e)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        hasError = true,
                        registerSuccess = false
                    )
                }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private suspend fun loadAddress(zipCode: String) {

        _uiState.update {
            it.copy(
                isLoading = true, hasError = false
            )
        }

        try {
            val address = cepService.getEnderecoByCep(zipCode)
            _uiState.update {

                it.copy(
                    street = address.street,
                    city = address.city,
                    country = "Brasil",
                    neighborhood = address.neighborhood,
                    state = address.state,

                    isLoading = false,
                    hasError = false
                )
            }
            _focusEvent.emit(Unit)
        } catch (e: Exception) {
            Log.e("SignUpViewModel", "Error fetching address", e)
            _uiState.value.copy(
                hasError = true, isLoading = false
            )
        }
    }
}

private fun capitalizeName(fullName: String): String {
    return fullName.split(" ").joinToString(separator = " ") { word ->
        word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
}