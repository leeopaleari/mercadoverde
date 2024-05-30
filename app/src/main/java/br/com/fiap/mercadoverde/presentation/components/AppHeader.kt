package br.com.fiap.mercadoverde.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mercadoverde.R

@Composable
fun AppHeader(
//    navController: NavController,
    cartSize: Int,
    onSearchTextChange: (text: String) -> Unit,
) {
    Column {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(onClick = {
//            navController.navigate(Route.CART_SCREEN) {
//                popUpTo(navController.graph.findStartDestination().id) {
//                    saveState = true
//                }
//                launchSingleTop = true
//                restoreState = true
//            }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.cart_icon),
                    contentDescription = "Carrinho",
                    tint = Color(0xFF49454F)
                )

                if (cartSize > 0) {
                    Box(
                        modifier = Modifier
                            .offset(x = 9.dp, y = -6.dp)
                            .background(color = Color.Red, shape = CircleShape)
                            .height(18.dp)
                            .width(18.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = cartSize.toString(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier.offset(y = -2.dp)
                        )
                    }
                }
            }
        }

//        Box(modifier = Modifier.padding(top = 4.dp, start = 16.dp, end = 16.dp)) {
//        }
//        CustomTextField(
//            leadingIcon = {
//
//                Icon(
//                    painter = painterResource(id = R.drawable.baseline_search_24),
//                    contentDescription = "icone lupa",
//                    tint = Color(0xFF49454F)
//                )
//
//            },
//            trailingIcon = null,
//            fontSize = 14.sp,
//            placeholderText = "Buscar por nome...",
//            onChange = {
//                onSearchTextChange(it)
//            }
//        )
    }
}