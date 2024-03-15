package br.com.fiap.mercadoverde.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.mercadoverde.data.repository.ProductRepositoryImpl
import br.com.fiap.mercadoverde.domain.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val productRepository: ProductRepositoryImpl
) : ViewModel() {

    private val _cartItems = MutableLiveData<List<Product>>(emptyList())
    val cartItems: MutableLiveData<List<Product>> = _cartItems

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
            _cartItems.postValue(productRepository.getAll())
        }
    }

    suspend fun addItemToCart(product: Product) {
        viewModelScope.launch {
            val itemExists = _cartItems.value?.find { it.id == product.id }

            if (itemExists !== null) {
                productRepository.delete(product)
            } else {
                productRepository.insert(product)
            }
            _cartItems.postValue(productRepository.getAll())
        }
    }

    suspend fun addItemQty(product: Product) {
        viewModelScope.launch {
            productRepository.increaseItemQty(product.id)
            _cartItems.postValue(productRepository.getAll())
        }
    }

    suspend fun removeItemQty(product: Product) {
        viewModelScope.launch {
            if (product.quantidade == 1) {
                productRepository.delete(product)
            } else {
                productRepository.decreaseItemQty(product.id)
            }
            _cartItems.postValue(productRepository.getAll())
        }
    }

    suspend fun clearCartItems() {
        viewModelScope.launch {
            productRepository.clearCartItems()
            _cartItems.postValue(productRepository.getAll())
        }
    }
}