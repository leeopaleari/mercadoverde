package br.com.fiap.mercadoverde.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.fiap.mercadoverde.R
import br.com.fiap.mercadoverde.presentation.components.CustomTextField
import br.com.fiap.mercadoverde.presentation.components.MyCircularProgress
import br.com.fiap.mercadoverde.presentation.screens.home.composables.ProductCard
import br.com.fiap.mercadoverde.presentation.screens.home.viewmodel.HomeViewModel

data class Category(
    val nome: String,
    val imagem: Int
)

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by homeViewModel.uiState.collectAsState()
    var selectedCategory by remember {
        mutableStateOf("")
    }

//    val coroutineScope = rememberCoroutineScope()

//    val categoryList by homeViewModel.categoryList.observeAsState(initial = emptyList())
//    val productList by homeViewModel.productList.observeAsState(initial = emptyList())
//
//    val filteredProductList = remember {
//        mutableStateOf(productList)
//    }

//    val cartItems by cartViewModel.cartItems.observeAsState()

//    LaunchedEffect(key1 = Unit) {
//        cartViewModel.loadCartItems()
//        snackbarHostState.showSnackbar(
//            message = "Mensagem do Snackbar"
//        )
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
//        Box(
//            modifier = Modifier.padding(16.dp),
//
//            ) {
//
//            CustomTextField(
//                leadingIcon = {
//
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_search_24),
//                        contentDescription = "icone lupa",
//                        tint = Color(0xFF49454F)
//                    )
//
//                },
//                trailingIcon = null,
//                fontSize = 14.sp,
//                placeholderText = "Buscar por nome...",
//                onChange = {
////                onSearchTextChange(it)
//                })
//        }
//        Spacer(modifier = Modifier.height(12.dp))

//        LazyRow(
//            contentPadding = PaddingValues(
//                start = 16.dp,
//                end = 16.dp,
//            ),
//            horizontalArrangement = Arrangement.spacedBy(10.dp)
//        ) {
//            items(categoryList) { category ->
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    CategoryCard(
//                        category = category,
//                        onCategoryClick = {
//                            if (selectedCategory == category.nome) {
//                                selectedCategory = ""
//                                filteredProductList.value = productList
//                            } else {
//                                selectedCategory = category.nome
//                                filteredProductList.value =
//                                    productList.filter {
//                                        it.categoria.contains(
//                                            selectedCategory,
//                                            ignoreCase = true
//                                        )
//                                    }
//                            }
//                        },
//                        selected = category.nome == selectedCategory
//                    )
//                    Text(text = category.nome, fontFamily = Inter, color = TextColor)
//                }
//            }
//        }
        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.isLoading) {
            MyCircularProgress(text = "Buscando Produtos", showBackground = false)
        } else if (uiState.products.isEmpty()) {
            Text(text = "No products found")
        } else {
            LazyVerticalGrid(
//                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
            ) {
                items(uiState.products) { product ->
                    ProductCard(
                        product = product,
                        onSelectProduct = {
//                        coroutineScope.launch {
////                            cartViewModel.addItemToCart(product)
//                        }
                        },
//                    selected = cartItems!!.any { it.id == product.id }
                    )
                }
            }
        }
    }

}
