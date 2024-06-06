package br.com.fiap.mercadoverde.presentation.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.successLogout) {
        onNavigateToAuth()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        uiState.userData?.let { userData ->
            Text(
                text = userData.name,
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
            Text(text = userData.name, fontFamily = Inter, color = Color.Black)
            Text(text = userData.email, fontFamily = Inter, color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Endereço para Entrega",
                fontFamily = Inter,
                color = Color(0xFF7B7B7B)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = userData.street, fontFamily = Inter, color = Color.Black)
            Text(
                text = "${userData.neighborhood}, ${userData.city}/${userData.state}",
                fontFamily = Inter,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Dados de Pagamento",
                fontFamily = Inter,
                color = Color(0xFF7B7B7B)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Cartão de Crédito", fontFamily = Inter, color = Color.Black)
            Text(text = "0000 0000 000 **", fontFamily = Inter, color = Color.Black)

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                modifier = Modifier.fillMaxWidth(0.7f).align(Alignment.CenterHorizontally),
                onClick = {
                    viewModel.logout()
                },
                colors = ButtonDefaults.outlinedButtonColors(
//                    containerColor = Color.Red,
                    contentColor = Color.Red
                ),
                border = BorderStroke(width = 2.dp, color = Color.Red)
            ) {
                Text(text = "Sair")
            }

            Spacer(modifier = Modifier.height(20.dp))

        } ?: run {
            // Exibir um indicador de carregamento enquanto os dados estão sendo carregados
            CircularProgressIndicator(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
        }
    }
}
