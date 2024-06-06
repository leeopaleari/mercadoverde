package br.com.fiap.mercadoverde.presentation.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.fiap.mercadoverde.presentation.screens.profile.viewmodel.ProfileScreeViewModel
import br.com.fiap.mercadoverde.presentation.theme.Inter

@Composable
fun ProfileScreen(
    onNavigateToAuth: () -> Unit,
    viewModel: ProfileScreeViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Leonardo",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Dados Pessoais",
                fontFamily = Inter,
                color = Color(0xFF7B7B7B)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Leonardo", fontFamily = Inter, color = Color.Black)
            Text(text = "leonardo@gmail.com", fontFamily = Inter, color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Endereço para Entrega",
                fontFamily = Inter,
                color = Color(0xFF7B7B7B)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Rua A, N 1", fontFamily = Inter, color = Color.Black)
            Text(text = "Bairro Andorinhas, Campinas/SP", fontFamily = Inter, color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Dados de Pagamento",
                fontFamily = Inter,
                color = Color(0xFF7B7B7B)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Cartão de Crédito", fontFamily = Inter, color = Color.Black)
            Text(text = "0000 0000 000 **", fontFamily = Inter, color = Color.Black)
        }

        Button(onClick = {
            viewModel.logout()
            onNavigateToAuth()
        }) {
            Text(text = "Sair")
        }
    }


}