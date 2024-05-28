package br.com.fiap.mercadoverde.presentation.screens.auth.SignUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.fiap.mentora.common.button.BaseButton
import br.com.fiap.mercadoverde.presentation.components.BaseInputField

@Composable
fun SignUpScreen(
    onPopBackStack: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()

            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {

        Text(text = "Criar Conta", style = MaterialTheme.typography.titleLarge.copy())

        Spacer(modifier = Modifier.height(6.dp))

        BaseInputField(
            onValueChange = {
//                uiState.onNameChange(it)
            },
            label = "Nome completo",
            placeholder = "Digite seu nome completo"
        )

        Spacer(modifier = Modifier.height(6.dp))


        BaseInputField(
            onValueChange = {
//                uiState.onEmailChange(it)
            },
            label = "E-mail",
            placeholder = "exemplo@email.com"
        )

        Spacer(modifier = Modifier.height(6.dp))

        BaseInputField(
            onValueChange = {
//                uiState.onPasswordChange(it)
            },
            label = "Senha",
            placeholder = "Digite sua senha",
            keyboardType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.height(6.dp))

        BaseInputField(
            onValueChange = {
            },
            label = "CEP",
            placeholder = "Digite seu CEP",
        )

        Spacer(modifier = Modifier.height(6.dp))

        BaseInputField(
            onValueChange = {
            },
            label = "Cidade",
            placeholder = "Sua cidade",
            keyboardType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.height(6.dp))

        BaseInputField(
            onValueChange = {
            },
            label = "Rua",
            placeholder = "Sua rua",
            keyboardType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.height(6.dp))

        BaseInputField(
            onValueChange = {
            },
            label = "Nº",
            placeholder = "",
            keyboardType = KeyboardType.Password
        )


        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BaseButton(
                text = "Voltar",
                onClick = onPopBackStack,
                modifier = Modifier.weight(0.5f),
            )
            Spacer(modifier = Modifier.width(16.dp))
            BaseButton(
                text = "Cadastrar",
                onClick = {
                },
                modifier = Modifier.weight(0.5f)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}