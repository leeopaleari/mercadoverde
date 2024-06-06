package br.com.fiap.mercadoverde.presentation.screens.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mercadoverde.domain.models.Product
import br.com.fiap.mercadoverde.presentation.theme.Inter
import br.com.fiap.mercadoverde.presentation.theme.PrimaryColor
import br.com.fiap.mercadoverde.presentation.theme.TextColor
import br.com.fiap.mercadoverde.presentation.theme.TextLightColor
import br.com.fiap.mercadoverde.utils.decodeBase64ToBitmap
import br.com.fiap.mercadoverde.utils.formatCurrency


@Composable
fun ProductCard(
    product: Product, onSelectProduct: () -> Unit, selected: Boolean = false
) {

    Card(
        modifier = Modifier.border(
            width = if (selected) 4.dp else 1.dp,
            color = if (selected) PrimaryColor else Color(0xFFD1D1D1),
            shape = RoundedCornerShape(10)
        ), colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        ), shape = RoundedCornerShape(20.dp)
    ) {
        Box {

            Column(
                modifier = Modifier.padding(12.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "Unidade",
                        fontFamily = Inter,
                        fontSize = 11.sp,
                        modifier = Modifier
                            .padding(4.dp)
                            .background(
                                color = PrimaryColor, shape = RoundedCornerShape(50)
                            )
                            .padding(horizontal = 8.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {

                    val bitmap = decodeBase64ToBitmap(product.image)
                    Image(
                        painter = BitmapPainter(bitmap.asImageBitmap()),
                        contentDescription = "Imagem do produto",
                        modifier = Modifier
                            .size(110.dp)
                            .padding(8.dp)
                    )
                }

                Text(
                    text = product.name,
                    color = TextLightColor,
                    fontFamily = Inter,
                    fontSize = 14.sp
                )



                Text(
                    text = formatCurrency(product.priceMonetary),
                    color = TextColor,
                    fontFamily = Inter,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )

            }
            FloatingActionButton(
                onClick = {
                    onSelectProduct()
                },
                modifier = Modifier
                    .padding(14.dp)
                    .width(40.dp)
                    .height(40.dp)
                    .align(alignment = Alignment.BottomEnd),
                containerColor = PrimaryColor,
                shape = RoundedCornerShape(100)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Adicionar ao carrinho",
                    tint = Color.White
                )
            }
        }
    }
}

