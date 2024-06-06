package br.com.fiap.mercadoverde.presentation.screens.profile.state

import br.com.fiap.mercadoverde.data.source.remote.model.Profile

data class ProfileScreenUiState(
    val userData: Profile? = null,
    val successLogout: Boolean = false
)