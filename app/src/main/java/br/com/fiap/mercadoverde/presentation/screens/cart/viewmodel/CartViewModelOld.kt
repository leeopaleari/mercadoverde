package br.com.fiap.mercadoverde.presentation.screens.cart.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.mercadoverde.data.repository.ProductRepositoryImpl
import br.com.fiap.mercadoverde.data.source.local.product.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModelOld @Inject constructor(
    private val productRepository: ProductRepositoryImpl
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<ProductEntity>>(emptyList())
    val cartItems = _cartItems.asStateFlow()

    private var _onLoading by mutableStateOf(false)
//    val onLoading: Boolean
//        get() = _onLoading

    init {
        loadCartItems()
        Log.d(TAG, "$TAG: init")
    }

    companion object {
        val TAG = "CartViewModel"
    }

    fun loadCartItems() {
        viewModelScope.launch {
            _cartItems.update { productRepository.getAll() }
        }
    }

    suspend fun addItemToCart(productEntity: ProductEntity) {
        viewModelScope.launch {
            val itemExists = _cartItems.value?.find { it.id == productEntity.id }

            if (itemExists !== null) {
                productRepository.delete(productEntity)
            } else {
                productRepository.insert(productEntity)
            }
            _cartItems.update {productRepository.getAll()}
        }
    }

    suspend fun addItemQty(productEntity: ProductEntity) {
        viewModelScope.launch {
            productRepository.increaseItemQty(productEntity.id)
            _cartItems.update {productRepository.getAll()}
        }
    }

    suspend fun removeItemQty(productEntity: ProductEntity) {
        viewModelScope.launch {
            if (productEntity.quantidade == 1) {
                productRepository.delete(productEntity)
            } else {
                productRepository.decreaseItemQty(productEntity.id)
            }
            _cartItems.update {productRepository.getAll()}
        }
    }

    suspend fun clearCartItems() {
        viewModelScope.launch {
            productRepository.clearCartItems()
            _cartItems.update {productRepository.getAll()}
        }
    }
}