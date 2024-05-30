package br.com.fiap.mercadoverde.presentation.screens.auth.SignIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.fiap.mercadoverde.R
import br.com.fiap.mercadoverde.presentation.components.BaseInputField
import br.com.fiap.mercadoverde.presentation.components.button.BaseButton
import br.com.fiap.mercadoverde.presentation.screens.auth.SignIn.viewmodel.AuthViewModel
import br.com.fiap.mercadoverde.presentation.theme.Inter
import br.com.fiap.mercadoverde.presentation.theme.PrimaryColor

@Composable
fun SignInScreen(
    onNavigateToSignUpScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarEvent = viewModel.snackbarEvent.collectAsState(initial = null)

    LaunchedEffect(snackbarEvent.value) {
        snackbarEvent.value?.let { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    if (uiState.isLoginSuccess) onNavigateToHomeScreen()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Box {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo mercado verde",
                    modifier = Modifier
                        .size(80.dp, Dp.Unspecified)
                        .aspectRatio(1f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Login", style = MaterialTheme.typography.titleLarge.copy())
            }
        }

        Box {
            Column {

                BaseInputField(
                    value = uiState.email,
                    onValueChange = {
                        uiState.onEmailChange(it)
                    },
                    label = "E-mail",
                    placeholder = "Digite seu e-mail"
                )

                Spacer(modifier = Modifier.height(12.dp))

                BaseInputField(
                    value = uiState.password,
                    onValueChange = {
                        uiState.onPasswordChange(it)
                    },
                    keyboardType = KeyboardType.Password,
                    label = "Senha",
                    placeholder = "Digite sua senha"
                )

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        modifier = Modifier
                            .clickable {

                            },
                        textDecoration = TextDecoration.Underline,
                        text = "Esqueceu sua senha?",
                        fontSize = 14.sp,
                        textAlign = TextAlign.End
                    )
                }
            }
        }

        Box {
            Column {
                BaseButton(
                    text = "Entrar",
                    onClick = {
                        uiState.onLoginClick()

                        if(uiState.isLoginSuccess) {
                            onNavigateToHomeScreen()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.7f)
                )

                Spacer(modifier = Modifier.height(20.dp))


                Row() {
                    Text(text = "Não é cadastrado? ", fontSize = 14.sp)
                    Text(
                        modifier = Modifier.clickable {
                            onNavigateToSignUpScreen()
                        },
                        text = "Criar uma conta",
                        fontSize = 14.sp,
                        color = PrimaryColor,
                        fontFamily = Inter,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}