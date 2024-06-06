package br.com.fiap.mercadoverde.presentation.screens.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.fiap.mercadoverde.presentation.screens.cart.viewmodel.CartViewModel
import br.com.fiap.mercadoverde.presentation.theme.Inter
import br.com.fiap.mercadoverde.presentation.theme.PrimaryColor
import br.com.fiap.mercadoverde.presentation.theme.TextLightColor
import br.com.fiap.mercadoverde.utils.decodeBase64ToBitmap
import br.com.fiap.mercadoverde.utils.formatCurrency

@Composable
fun CartScreen(viewModel: CartViewModel = hiltViewModel()) {
    val cartItems by viewModel.cartItems.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadCartItems()
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        if (cartItems.isEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nenhum item adicionado no carrinho",
                    fontFamily = Inter,
                    fontSize = 18.sp,
                    color = TextLightColor
                )
            }
        }

        if (cartItems.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = {
                    viewModel.clearCartItems()
                }) {
                    Text(
                        text = "Limpar carrinho",
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        fontFamily = Inter
                    )
                }
            }
            LazyColumn {

            }
            LazyColumn(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(cartItems) { item ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        Card(
                            modifier = Modifier
                                .weight(2f)
                                .height(60.dp),
                            border = BorderStroke(width = 1.dp, color = Color.Gray)
                        ) {
                            Row(
                                modifier = Modifier
                                    .background(color = Color.White)
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .height(60.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {

                                val bitmap = decodeBase64ToBitmap(item.image)
                                Image(
                                    painter = BitmapPainter(bitmap.asImageBitmap()),
                                    contentDescription = "Imagem do produto",
                                    modifier = Modifier
                                        .padding(end = 6.dp)
                                        .width(40.dp)
                                )
                                Text(
                                    text = item.name,
                                    fontSize = 14.sp,
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(2f),
                                    color = TextLightColor
                                )
                                Text(
                                    text = formatCurrency(item.price),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = Inter,
                                    color = Color.Black
                                )
                            }
                        }

                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .height(60.dp),
                            border = BorderStroke(width = 1.dp, color = Color.Gray)
                        ) {
                            Row(
                                modifier = Modifier
                                    .background(color = Color.White)
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .height(60.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedButton(
                                    onClick = {
                                        viewModel.removeItemQty(item)
                                    },
                                    modifier = Modifier.weight(1f),
                                    contentPadding = PaddingValues(
                                        4.dp
                                    ),
                                    border = BorderStroke(width = 0.dp, color = Color.White)
                                ) {
                                    Text(text = "-", color = PrimaryColor)
                                }

                                Text(
                                    text = item.quantity.toString(),
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black
                                )

                                OutlinedButton(
                                    onClick = {
                                        viewModel.addItemQty(item)
                                    },
                                    modifier = Modifier.weight(1f),
                                    contentPadding = PaddingValues(
                                        0.dp
                                    ),
                                    border = BorderStroke(width = 0.dp, color = Color.White)
                                ) {
                                    Text(text = "+", color = PrimaryColor)
                                }

                            }
                        }
                    }
                }
            }
        }

//        if (cartItems.isNotEmpty())
//            Box(
//                modifier = Modifier
//                    .background(color = PrimaryColor)
//                    .padding(vertical = 8.dp)
//                    .fillMaxWidth(),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = formatCurrency(cartItems.sumOf { it.preco.toDouble() * it.quantidade }
//                        .toFloat()),
//                    color = Color.White,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.ExtraBold,
//                    fontFamily = Inter,
//                )
//            }
    }

}
