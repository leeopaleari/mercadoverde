package br.com.fiap.mercadoverde.presentation.screens.home.uistate

import br.com.fiap.mercadoverde.network.model.Product

data class HomeScreenUiState(
    val products: List<Product> = emptyList(),

    val isLoading: Boolean = false,
    val hasError: Boolean = false
)