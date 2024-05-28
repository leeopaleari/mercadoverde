package br.com.fiap.mercadoverde.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.fiap.mercadoverde.presentation.components.AppHeader
import br.com.fiap.mercadoverde.presentation.screens.cart.viewmodel.CartViewModel
import br.com.fiap.mercadoverde.presentation.screens.home.composables.CategoryCard
import br.com.fiap.mercadoverde.presentation.screens.home.composables.ProductCard
import br.com.fiap.mercadoverde.presentation.screens.home.viewmodel.HomeViewModel
import br.com.fiap.mercadoverde.presentation.theme.Inter
import br.com.fiap.mercadoverde.presentation.theme.TextColor
import kotlinx.coroutines.launch

data class Category(
    val nome: String,
    val imagem: Int
)

@Composable
fun HomeScreen(
//    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {

    var selectedCategory by remember {
        mutableStateOf("")
    }

    val coroutineScope = rememberCoroutineScope()

    val categoryList by homeViewModel.categoryList.observeAsState(initial = emptyList())
    val productList by homeViewModel.productList.observeAsState(initial = emptyList())

    val filteredProductList = remember {
        mutableStateOf(productList)
    }

    val cartItems by cartViewModel.cartItems.observeAsState()

    LaunchedEffect(key1 = Unit) {
        cartViewModel.loadCartItems()
//        snackbarHostState.showSnackbar(
//            message = "Mensagem do Snackbar"
//        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppHeader(
            cartItems!!.size,
            onSearchTextChange = { text ->
                if (text.isNotEmpty()) {
                    filteredProductList.value =
                        productList.filter { it.nome.contains(text, ignoreCase = true) }

                } else {
                    filteredProductList.value = productList
                }
            })
        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(categoryList) { category ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CategoryCard(
                        category = category,
                        onCategoryClick = {
                            if (selectedCategory == category.nome) {
                                selectedCategory = ""
                                filteredProductList.value = productList
                            } else {
                                selectedCategory = category.nome
                                filteredProductList.value =
                                    productList.filter {
                                        it.categoria.contains(
                                            selectedCategory,
                                            ignoreCase = true
                                        )
                                    }
                            }
                        },
                        selected = category.nome == selectedCategory
                    )
                    Text(text = category.nome, fontFamily = Inter, color = TextColor)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
        ) {
            items(filteredProductList.value) { product ->
                ProductCard(
                    product = product,
                    onSelectProduct = {
                        coroutineScope.launch {
//                            cartViewModel.addItemToCart(product)
                        }
                    },
                    selected = cartItems!!.any { it.id == product.id }
                )
            }
        }
    }

}
