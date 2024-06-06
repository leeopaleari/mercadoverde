package br.com.fiap.mercadoverde.presentation.screens.cart.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.mercadoverde.data.repository.CartRepositoryImpl
import br.com.fiap.mercadoverde.data.source.local.cart.CartItemEntity
import br.com.fiap.mercadoverde.data.source.remote.model.CreateOrderRequest
import br.com.fiap.mercadoverde.data.source.remote.model.OrderItem
import br.com.fiap.mercadoverde.data.source.remote.services.OrderService
import br.com.fiap.mercadoverde.domain.models.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepositoryImpl,
    private val orderService: OrderService
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItemEntity>>(emptyList())
    val cartItems: StateFlow<List<CartItemEntity>> = _cartItems

    private val _totalAmount = MutableStateFlow(0.0)
    val totalAmount: StateFlow<Double> = _totalAmount

    private val _snackbarEvent = MutableSharedFlow<SnackbarMessage>()
    val snackbarEvent: SharedFlow<SnackbarMessage> = _snackbarEvent

    init {
        loadCartItems()
    }

    fun loadCartItems() {
        viewModelScope.launch {
            val items = cartRepository.getAll()
            _cartItems.value = items
            updateTotalAmount(items)
        }
    }

    private fun updateTotalAmount(cartItems: List<CartItemEntity>) {
        _totalAmount.value = cartItems.sumOf { it.price * it.quantity }
    }

    fun addItemQty(productEntity: CartItemEntity) {
        viewModelScope.launch {
            cartRepository.increaseItemQty(productEntity.productId)
            loadCartItems()
        }
    }

    fun removeItemQty(productEntity: CartItemEntity) {
        viewModelScope.launch {
            if (productEntity.quantity == 1) {
                cartRepository.delete(productEntity)
            } else {
                cartRepository.decreaseItemQty(productEntity.productId)
            }
            loadCartItems()
        }
    }

    fun createOrder() {
        viewModelScope.launch {
            try {
                val orderItems = cartItems.value.map { cartItem ->
                    OrderItem(
                        productId = cartItem.productId,
                        quantity = cartItem.quantity
                    )
                }

                val orderRequest = CreateOrderRequest(orderItems)

                orderService.createOrder(orderRequest)
                clearCartItems()
                _snackbarEvent.emit(
                    SnackbarMessage(
                        "Pedido criado com sucesso",
                        System.currentTimeMillis()
                    )
                )
            } catch (t: Throwable) {
                Log.e("CartViewModel", "Error createOrder: ${t.message}", t)
            }
        }

    }

    fun clearCartItems() {
        viewModelScope.launch {
            cartRepository.clearCartItems()
            loadCartItems()
        }
    }

}