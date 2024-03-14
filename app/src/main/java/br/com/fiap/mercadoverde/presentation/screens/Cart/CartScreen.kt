package br.com.fiap.mercadoverde.presentation.screens.Cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.mercadoverde.R
import br.com.fiap.mercadoverde.ui.common.AppHeader
import br.com.fiap.mercadoverde.ui.theme.Inter
import br.com.fiap.mercadoverde.ui.theme.PrimaryColor
import br.com.fiap.mercadoverde.ui.theme.TextLightColor

@Composable
fun CartScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppHeader(navController, true)
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(100) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    Card(
                        modifier = Modifier
                            .weight(2f)
                            .height(50.dp),
                        border = BorderStroke(width = 1.dp, color = Color.Gray)
                    ) {
                        Row(
                            modifier = Modifier
                                .background(color = Color.White)
                                .fillMaxWidth()
                                .padding(8.dp)
                                .height(50.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.kiwi),
                                contentDescription = "Imagem do produto",
                                modifier = Modifier.padding(end = 6.dp)
                            )
                            Text(
                                text = "Kiwi",
                                fontSize = 14.sp,
                                fontFamily = Inter,
                                modifier = Modifier.weight(2f),
                                color = TextLightColor
                            )
                            Text(
                                text = "R$1,00",
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
                            .height(50.dp),
                        border = BorderStroke(width = 1.dp, color = Color.Gray)
                    ) {
                        Row(
                            modifier = Modifier
                                .background(color = Color.White)
                                .fillMaxWidth()
                                .padding(8.dp)
                                .height(50.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.weight(1f),
                                contentPadding = PaddingValues(
                                    4.dp
                                ),
                                border = BorderStroke(width = 0.dp, color = Color.White)
                            ) {
                                Text(text = "-", color = PrimaryColor)
                            }

                            Text(text = "1", fontFamily = Inter, fontWeight = FontWeight.ExtraBold)

                            OutlinedButton(
                                onClick = { /*TODO*/ },
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

        Box(
            modifier = Modifier
                .background(color = PrimaryColor)
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Total: R$ 3,50",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }

}