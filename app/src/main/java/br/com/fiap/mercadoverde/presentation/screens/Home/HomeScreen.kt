package br.com.fiap.mercadoverde.presentation.screens.Home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mercadoverde.R
import br.com.fiap.mercadoverde.presentation.screens.Home.composables.CategoryCard
import br.com.fiap.mercadoverde.presentation.screens.Home.composables.ProductCard
import br.com.fiap.mercadoverde.ui.common.CustomTextField
import br.com.fiap.mercadoverde.ui.theme.Inter
import br.com.fiap.mercadoverde.ui.theme.TextColor

data class Product(
    val nome: String,
    val preco: String,
    val avaliacao: Float,
    val imagem: Int
)

data class Category(
    val nome: String,
    val imagem: Int
)
@Composable
fun HomeScreen() {

    val productList = listOf<Product>(
        Product("Laranja", "1,00", 3.3f, R.drawable.laranja),
        Product("Kiwi", "2,00", 3.3f, R.drawable.kiwi),
        Product("Pimentao", "5,50", 3.3f, R.drawable.pimentao),
        Product("Limão Siciliano", "4,30", 3.3f, R.drawable.limao_siciliano),
        Product("Alface", "2,00", 3.3f, R.drawable.alface),
        Product("Cebola", "3,30", 3.3f, R.drawable.cebola),
    )

    val categoryList = listOf<Category>(
        Category("Caules", R.drawable.caules),
        Category("Frutas", R.drawable.frutas),
        Category("Legumes", R.drawable.legumes),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)) {
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
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(categoryList) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CategoryCard(category = it)
                    Text(text = it.nome, fontFamily = Inter, color = TextColor)
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
            items(productList) {
                ProductCard(product = it)
            }
        }
    }
}


