package br.com.fiap.mercadoverde.presentation.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.mercadoverde.R
import br.com.fiap.mercadoverde.domain.models.Product
import br.com.fiap.mercadoverde.domain.services.SecureStorageService
import br.com.fiap.mercadoverde.network.model.ProductResponse
import br.com.fiap.mercadoverde.network.services.ProductService
import br.com.fiap.mercadoverde.presentation.screens.home.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val secureStorage: SecureStorageService,
    private val productService: ProductService
) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductResponse>>(emptyList<ProductResponse>())

    init {
        viewModelScope.launch {
            loadProducts()
        }
    }

    private suspend fun loadProducts() {
        try {
            val products = productService.getProducts()
            val teste = ""
        } catch (t: Throwable) {
            Log.e("HomeViewModel", "loadUsers: ${t.message}", t)
        }

    }

    private val _categoryList = MutableLiveData<List<Category>>(
        listOf(
            Category("Verduras", R.drawable.verduras),
            Category("Frutas", R.drawable.frutas),
            Category("Legumes", R.drawable.legumes),
        )
    )
    val categoryList: MutableLiveData<List<Category>> = _categoryList

    private val _productList = MutableLiveData<List<Product>>(
        listOf(
            Product(1, "Laranja", "Frutas", 1.00f, R.drawable.laranja, "Unidade"),
            Product(2, "Kiwi", "Frutas", 2.00f, R.drawable.kiwi, "Unidade"),
            Product(3, "Pimentao", "", 5.50f, R.drawable.pimentao, "Unidade"),
            Product(4, "Limão Siciliano", "Frutas", 4.30f, R.drawable.limao_siciliano, "Unidade"),
            Product(5, "Alface", "Verduras", 2.00f, R.drawable.alface, "Unidade"),
            Product(6, "Cebola", "Legumes", 3.30f, R.drawable.cebola, "Unidade"),
        )
    )
    val productList: MutableLiveData<List<Product>> = _productList


}