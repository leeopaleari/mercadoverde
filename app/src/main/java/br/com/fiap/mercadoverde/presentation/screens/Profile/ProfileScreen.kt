package br.com.fiap.mercadoverde.presentation.screens.Profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.mercadoverde.ui.common.AppHeader
import br.com.fiap.mercadoverde.ui.theme.Inter

@Composable
fun ProfileScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppHeader(navController, false)
        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Leonardo",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            //Icon(Icons.Outlined.Edit, contentDescription = "Edit Button")

            Spacer(modifier = Modifier.height(20.dp))
            Text(text  ="Dados Pessoais",
                fontFamily = Inter,
                color = Color(0xFF7B7B7B)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Leonardo")
            Text(text = "leonardo@gmail.com")

            Spacer(modifier = Modifier.height(20.dp))
            Text(text  ="Endereço para Entrega",
                fontFamily = Inter,
                color = Color(0xFF7B7B7B)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Rua A, N 1")
            Text(text = "Bairro Andorinhas, Campinas/SP")

            Spacer(modifier = Modifier.height(20.dp))
            Text(text  ="Dados de Pagamento",
                fontFamily = Inter,
                color = Color(0xFF7B7B7B)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Cartão de Crédito")
            Text(text = "0000 0000 000 **")


        }
    }


}