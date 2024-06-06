package br.com.fiap.mercadoverde.presentation.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.mercadoverde.data.source.remote.services.ProductService
import br.com.fiap.mercadoverde.domain.models.Product
import br.com.fiap.mercadoverde.presentation.screens.home.uistate.HomeScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productService: ProductService
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _snackbarEvent = MutableSharedFlow<SnackbarMessage>()
    val snackbarEvent: SharedFlow<SnackbarMessage> = _snackbarEvent

    init {
        viewModelScope.launch {
            loadAllData()
        }
    }


    fun selectCategory(categoryName: String) {
        viewModelScope.launch {
            val currentSelectedCategory = _uiState.value.selectedCategory
            if (currentSelectedCategory == categoryName) {
                // Se a categoria selecionada for a mesma, desmarque e carregue todos os produtos
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        selectedCategory = null,
                        errorMessage = null
                    )
                }
                try {
                    val productResponse = productService.getProducts()
                    _uiState.update {
                        it.copy(
                            products = productResponse.products,
                            isLoading = false
                        )
                    }
                } catch (t: Throwable) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = t.message
                        )
                    }
                    Log.e("HomeViewModel", "Error loading data: ${t.message}", t)
                }
            } else {
                // Caso contrário, selecione a nova categoria e carregue os produtos dessa categoria
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        selectedCategory = categoryName,
                        errorMessage = null
                    )
                }
                try {
                    val productResponse = productService.getProductsByCategory(categoryName)
                    _uiState.update {
                        it.copy(
                            products = productResponse.products,
                            isLoading = false
                        )
                    }
                } catch (t: Throwable) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = t.message
                        )
                    }
                    Log.e("HomeViewModel", "Error loading data: ${t.message}", t)
                }
            }
        }
    }

    fun addToCart(product: Product) {
        Log.i("HomeViewModel", "addToCart: $product")
        viewModelScope.launch {

            // TODO: Chamar api para adicionar ao cart
            _snackbarEvent.emit(SnackbarMessage("${product.name} adicionado ao carrinho!", System.currentTimeMillis()))
        }

    }

    private suspend fun loadAllData() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        try {
            val productResponse = productService.getProducts()
            val categoryResponse = productService.getCategories()

            _uiState.update {
                it.copy(
                    products = productResponse.products,
                    categories = categoryResponse.categories,
                    isLoading = false
                )
            }
        } catch (t: Throwable) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    errorMessage = t.message
                )
            }
            Log.e("HomeViewModel", "Error loading data: ${t.message}", t)
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            loadAllData()
        }
    }
}

data class SnackbarMessage(val message: String, val timestamp: Long)