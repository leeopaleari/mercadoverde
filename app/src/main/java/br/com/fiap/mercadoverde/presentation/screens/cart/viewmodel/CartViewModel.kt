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
//    private val _totalItemsInCart = MutableStateFlow(0)
//    val totalItemsInCart: StateFloaw<Int> = _totalItemsInCart
//
//    private val _snackbarEvent = MutableSharedFlow<SnackbarMessage>()
//    val snackbarEvent: SharedFlow<SnackbarMessage> = _snackbarEvent
//
//    fun addToCart(product: Product) {
//        viewModelScope.launch {
//            try {
//                val updatedCartItems = _cartItems.value.toMutableList()
//                val existingItemIndex =
//                    updatedCartItems.indexOfFirst { it.product.id == product.id }
//                if (existingItemIndex != -1) {
//                    val existingItem = updatedCartItems[existingItemIndex]
//                    updatedCartItems[existingItemIndex] =
//                        existingItem.copy(quantity = existingItem.quantity + 1)
//                } else {
//                    updatedCartItems.add(CartItem(product, 1))
//                }
//                _cartItems.value = updatedCartItems
//                updateTotalItemsInCart()
//            } catch (t: Throwable) {
//                _snackbarEvent.emit(
//                    SnackbarMessage(
//                        "Erro ao adicionar produto ao carrinho: ${t.message}",
//                        System.currentTimeMillis()
//                    )
//                )
//                Log.e("CartViewModel", "Error adding product to cart: ${t.message}", t)
//            }
//        }
//    }
//
//    fun removeFromCart(product: Product) {
//        viewModelScope.launch {
//            val updatedCartItems = _cartItems.value.toMutableList()
//            val existingItemIndex = updatedCartItems.indexOfFirst { it.product.id == product.id }
//            if (existingItemIndex != -1) {
//                val existingItem = updatedCartItems[existingItemIndex]
//                if (existingItem.quantity > 1) {
//                    updatedCartItems[existingItemIndex] =
//                        existingItem.copy(quantity = existingItem.quantity - 1)
//                } else {
//                    updatedCartItems.removeAt(existingItemIndex)
//                }
//                _cartItems.value = updatedCartItems
//                updateTotalItemsInCart()
//                _snackbarEvent.emit(
//                    SnackbarMessage(
//                        "Produto removido do carrinho com sucesso!",
//                        System.currentTimeMillis()
//                    )
//                )
//            }
//        }
//    }
//
//    private fun updateTotalItemsInCart() {
//        _totalItemsInCart.value = _cartItems.value.sumOf { it.quantity }
//    }

}