package br.com.fiap.mercadoverde.presentation.screens.home.composables

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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import br.com.fiap.mercadoverde.domain.models.Category
import br.com.fiap.mercadoverde.presentation.theme.PrimaryColor
import br.com.fiap.mercadoverde.utils.decodeBase64ToBitmap

@Composable
fun CategoryCard(category: Category, onCategoryClick: () -> Unit = {}, selected: Boolean = false) {
    Card(
        modifier = Modifier
            .width(55.dp)
            .height(55.dp)
            .border(
                width = if (selected) 4.dp else 1.dp,
                color = if (selected) PrimaryColor else Color(0xFFD1D1D1),
                shape = RoundedCornerShape(20)
            ),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            onCategoryClick()
        }
    ) {
        val bitmap = decodeBase64ToBitmap(category.image)
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            painter = BitmapPainter(bitmap.asImageBitmap()),
            contentDescription = "Imagem categoria ${category.name}"
        )
    }
}
