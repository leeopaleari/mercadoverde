package br.com.fiap.mercadoverde.presentation.screens.profile.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.mercadoverde.data.source.remote.services.UserService
import br.com.fiap.mercadoverde.domain.services.SecureStorageService
import br.com.fiap.mercadoverde.presentation.screens.profile.state.ProfileScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreeViewModel @Inject constructor(
    private val secureStorage: SecureStorageService,
    private val userService: UserService

) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            try {

                val userData = userService.getProfile()

                _uiState.update {
                    it.copy(
                        userData = userData.user
                    )
                }
                Log.i("ProfileViewModel", "loadUserData: $userData")
            } catch (t: Throwable) {
                Log.e("ProfileScreeViewModel", "Error loadUserData: ${t.message}", t)
            }
        }
    }

    fun logout() {
        secureStorage.clearAccessToken()
        _uiState.update { it.copy(successLogout = true) }
    }
}