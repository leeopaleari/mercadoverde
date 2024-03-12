package br.com.fiap.mercadoverde.presentation.screens.Home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.fiap.mercadoverde.R
import br.com.fiap.mercadoverde.presentation.screens.Home.Category

@Composable
fun CategoryCard(category: Category) {
    Card(
        modifier = Modifier
            .width(55.dp)
            .height(55.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFD1D1D1),
                shape = RoundedCornerShape(20)
            ),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            painter = painterResource(id = category.imagem),
            contentDescription = "Imagem categoria ${category.nome}"
        )
    }
}
