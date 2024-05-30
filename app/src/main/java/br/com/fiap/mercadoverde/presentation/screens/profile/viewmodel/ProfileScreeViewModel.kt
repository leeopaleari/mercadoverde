package br.com.fiap.mercadoverde.presentation.screens.profile.viewmodel

import androidx.lifecycle.ViewModel
import br.com.fiap.mercadoverde.domain.services.SecureStorageService
import br.com.fiap.mercadoverde.presentation.screens.profile.uistate.ProfileScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileScreeViewModel @Inject constructor(
    private val secureStorage: SecureStorageService
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileScreenUiState())
    val uiState = _uiState.asStateFlow()
    fun logout() {
        secureStorage.clearAccessToken()
        _uiState.update { it.copy(successLogout = true) }
    }
}