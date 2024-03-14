package br.com.fiap.mercadoverde.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import br.com.fiap.mercadoverde.presentation.screens.Home.Product
import java.util.UUID

data class CartItem (
    val id: String,
    val product: Product,
    var quantity: Int
)

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateOf<List<CartItem>>(emptyList())
    val cartItems: State<List<CartItem>> = _cartItems

    private val _totalPrice = mutableDoubleStateOf(0.0)
    val totalPrice: State<Double> = _totalPrice

    private val _itemsQuantity = mutableIntStateOf(0)
    val itemsQuantity: State<Int> = _itemsQuantity

    fun addItemFromProduct(product: Product) {
       var item = _cartItems.value.find { it -> it.product.id == product.id }

        if(item == null) {
            val uuid = UUID.randomUUID()
            val uuidAsString = uuid.toString()

            item = CartItem(uuidAsString, product, 1)
            addItemToList(item)
        } else {
            item.quantity++
            updateTotalValues()
        }
    }

    fun updateItemQuantity(item: CartItem) {
        val existingItem = _cartItems.value.find { it -> it.id == item.id }

        if(existingItem != null) {
            existingItem.quantity = item.quantity
            updateTotalValues()
        }
    }

    fun addItemToList(item: CartItem) {
        val updatedCart = _cartItems.value.toMutableList()
        updatedCart.add(item)
        _cartItems.value = updatedCart

        updateTotalValues()
    }

    fun removeItemFromList(item: CartItem) {
        val updatedCart = _cartItems.value.toMutableList()
        updatedCart.remove(item)
        _cartItems.value = updatedCart

        updateTotalValues()
    }


    private fun updateTotalValues() {
        _totalPrice.doubleValue = _cartItems.value.sumOf { it.product.preco.toDouble() }
        _itemsQuantity.intValue = _cartItems.value.size
    }
}
