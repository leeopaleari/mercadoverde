package br.com.fiap.mercadoverde.presentation.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mercadoverde.network.model.Product
import br.com.fiap.mercadoverde.presentation.theme.Inter
import br.com.fiap.mercadoverde.presentation.theme.PrimaryColor
import br.com.fiap.mercadoverde.presentation.theme.TextColor
import br.com.fiap.mercadoverde.presentation.theme.TextLightColor
import br.com.fiap.mercadoverde.utils.formatCurrency


@Composable
fun ProductCard(
    product: Product,
    onSelectProduct: () -> Unit,
    selected: Boolean = false
) {

    Card(
        modifier = Modifier
            .border(
                width = if (selected) 4.dp else 1.dp,
                color = if (selected) PrimaryColor else Color(0xFFD1D1D1),
                shape = RoundedCornerShape(10)
            ),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            onSelectProduct()
        }
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
//                Image(
//                    painter = painterResource(id = product.i),
//                    contentDescription = "Imagem do produto",
//                    modifier = Modifier.size(110.dp)
//                )
            }

            Text(text = product.name, color = TextLightColor, fontFamily = Inter, fontSize = 14.sp)
            Text(
                text = formatCurrency(product.price.toDouble()),
                color = TextColor,
                fontFamily = Inter,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

