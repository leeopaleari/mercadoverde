package br.com.fiap.mercadoverde.presentation.screens.cart.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.mercadoverde.data.repository.CartRepositoryImpl
import br.com.fiap.mercadoverde.data.repository.ProductRepositoryImpl
import br.com.fiap.mercadoverde.data.source.local.cart.CartItemEntity
import br.com.fiap.mercadoverde.data.source.local.product.ProductEntity
import br.com.fiap.mercadoverde.domain.models.CartItem
import br.com.fiap.mercadoverde.domain.models.Product
import br.com.fiap.mercadoverde.domain.models.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepositoryImpl
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItemEntity>>(emptyList())
    val cartItems: StateFlow<List<CartItemEntity>> = _cartItems

    init {
        loadCartItems()
    }

    fun loadCartItems() {
        viewModelScope.launch {
            _cartItems.update { cartRepository.getAll() }
        }
    }

    fun addItemQty(productEntity: CartItemEntity) {
        viewModelScope.launch {
            cartRepository.increaseItemQty(productEntity.productId)
            _cartItems.update { cartRepository.getAll() }
        }
    }

    fun removeItemQty(productEntity: CartItemEntity) {
        viewModelScope.launch {
            if (productEntity.quantity == 1) {
                cartRepository.delete(productEntity)
            } else {
                cartRepository.decreaseItemQty(productEntity.productId)
            }
            _cartItems.update { cartRepository.getAll() }
        }
    }

    fun clearCartItems() {
        viewModelScope.launch {
            cartRepository.clearCartItems()
            _cartItems.update { cartRepository.getAll() }
        }
    }

}