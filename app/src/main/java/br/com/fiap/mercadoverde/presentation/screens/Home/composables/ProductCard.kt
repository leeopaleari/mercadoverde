package br.com.fiap.mercadoverde.presentation.screens.Home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mercadoverde.presentation.screens.Home.Product
import br.com.fiap.mercadoverde.ui.theme.Inter
import br.com.fiap.mercadoverde.ui.theme.PrimaryColor
import br.com.fiap.mercadoverde.ui.theme.TextColor
import br.com.fiap.mercadoverde.ui.theme.TextLightColor


@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFD1D1D1),
                shape = RoundedCornerShape(10)
            ),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Unidade",
                    fontFamily = Inter,
                    fontSize = 11.sp,
                    modifier = Modifier
                        .padding(4.dp)
                        .background(
                            color = PrimaryColor,
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 8.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = product.imagem),
                    contentDescription = "Imagem do produto",
                    modifier = Modifier.size(110.dp)
                )
            }

            Text(text = product.nome, color = TextLightColor, fontFamily = Inter, fontSize = 14.sp)
            Text(
                text = "R$ ${product.preco}",
                color = TextColor,
                fontFamily = Inter,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

