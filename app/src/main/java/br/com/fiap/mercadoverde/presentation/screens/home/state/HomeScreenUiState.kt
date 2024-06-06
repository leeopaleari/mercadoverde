package br.com.fiap.mercadoverde.presentation.screens.home.state

import br.com.fiap.mercadoverde.domain.models.Category
import br.com.fiap.mercadoverde.domain.models.Product

data class HomeScreenUiState(
    val products: List<Product> = emptyList(),
    val categories: List<Category> = emptyList(),
    val selectedCategory: String? = null,
    val filteredProducts: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)