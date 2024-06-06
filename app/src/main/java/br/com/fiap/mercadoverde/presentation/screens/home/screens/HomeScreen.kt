package br.com.fiap.mercadoverde.presentation.screens.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.fiap.mercadoverde.R
import br.com.fiap.mercadoverde.presentation.components.CustomTextField
import br.com.fiap.mercadoverde.presentation.components.MyCircularProgress
import br.com.fiap.mercadoverde.presentation.screens.home.composables.CategoryCard
import br.com.fiap.mercadoverde.presentation.screens.home.composables.ProductCard
import br.com.fiap.mercadoverde.presentation.screens.home.state.HomeScreenUiState
import br.com.fiap.mercadoverde.presentation.screens.home.viewmodel.HomeViewModel
import br.com.fiap.mercadoverde.presentation.theme.Inter
import br.com.fiap.mercadoverde.presentation.theme.TextColor
import br.com.fiap.mercadoverde.presentation.theme.TextLightColor

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {
    val snackbarEvent by homeViewModel.snackbarEvent.collectAsState(initial = null)

    LaunchedEffect(snackbarEvent) {
        snackbarEvent?.let { snackbarMessage ->
            snackbarHostState.showSnackbar(snackbarMessage.message)
        }
    }


    Content(homeViewModel = homeViewModel)
}

@Composable
fun Content(homeViewModel: HomeViewModel) {

    val uiState by homeViewModel.uiState.collectAsState()
    val searchQuery by homeViewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        SearchBar(onSearchQueryChange = { homeViewModel.updateSearchQuery(it) })

        Spacer(modifier = Modifier.height(12.dp))

        Categories(uiState = uiState, onCategoryClick = { categoryName ->
            homeViewModel.selectCategory(categoryName)
        })

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.isLoading) {
            MyCircularProgress(text = "Buscando Produtos...", showBackground = false)
        } else if (uiState.filteredProducts.isEmpty()) {
            NothingFound()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
            ) {
                items(uiState.filteredProducts) { product ->
                    ProductCard(
                        product = product,
                        onSelectProduct = {
                            homeViewModel.addToCart(product)
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun NothingFound() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Nenhum produto \n encontrado :(",
            fontFamily = Inter,
            color = TextLightColor,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun SearchBar(
    onSearchQueryChange: (String) -> Unit
) {
    Box(
        modifier = Modifier.padding(16.dp),

        ) {

        CustomTextField(
            leadingIcon = {

                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "icone lupa",
                    tint = Color(0xFF49454F)
                )

            },
            trailingIcon = null,
            fontSize = 14.sp,
            placeholderText = "Buscar por nome...",
            onChange = onSearchQueryChange
        )
    }
}

@Composable
fun Categories(uiState: HomeScreenUiState, onCategoryClick: (String) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
        ),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(uiState.categories) { category ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CategoryCard(
                    category = category,
                    onCategoryClick = {
                        onCategoryClick(category.name)
                    },
                    selected = category.name == uiState.selectedCategory
                )
                Text(text = category.name, fontFamily = Inter, color = TextColor)
            }
        }
    }
}