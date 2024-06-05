package br.com.fiap.mercadoverde.presentation.screens.auth.SignUp

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.fiap.mercadoverde.R
import br.com.fiap.mercadoverde.presentation.components.BaseInputField
import br.com.fiap.mercadoverde.presentation.components.MyCircularProgress
import br.com.fiap.mercadoverde.presentation.components.button.BaseButton
import br.com.fiap.mercadoverde.presentation.screens.auth.SignUp.state.SignUpUiState
import br.com.fiap.mercadoverde.presentation.screens.auth.SignUp.viewmodel.SignUpViewModel
import br.com.fiap.mercadoverde.presentation.theme.PrimaryColor

@Composable
fun SignUpScreen(
    onPopBackStack: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) MyCircularProgress("Carregando..")
    if (uiState.registerSuccess) onNavigateToHomeScreen()

    Content(
        onPopBackStack = onPopBackStack,
        uiState = uiState,
        viewModel = viewModel,
        onNavigateToHomeScreen = onNavigateToHomeScreen
    )
}

@Composable
fun Content(
    onPopBackStack: () -> Unit,
    uiState: SignUpUiState,
    viewModel: SignUpViewModel,
    onNavigateToHomeScreen: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        viewModel.focusEvent.collect {
            focusRequester.requestFocus()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo mercado verde",
            modifier = Modifier
                .size(80.dp, Dp.Unspecified)
                .aspectRatio(1f)
        )

        Box {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = "Criar conta", style = MaterialTheme.typography.titleLarge.copy())

                Spacer(modifier = Modifier.height(20.dp))

                BaseInputField(
                    onValueChange = {
                        uiState.onNameChange(it)
                    },
                    label = "Nome completo",
                    placeholder = "Digite seu nome"
                )

                Spacer(modifier = Modifier.height(6.dp))


                BaseInputField(
                    onValueChange = {
                        uiState.onEmailChange(it)
                    },
                    label = "E-mail",
                    placeholder = "Digite seu e-mail"
                )

                Spacer(modifier = Modifier.height(6.dp))

                BaseInputField(
                    onValueChange = {
                        uiState.onPasswordChange(it)
                    },
                    label = "Senha",
                    placeholder = "Digite sua senha",
                    keyboardType = KeyboardType.Password
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    BaseInputField(
                        modifier = Modifier.fillMaxWidth(0.4f),
                        onValueChange = {
                            uiState.onZipCodeChange(it)
                        },
                        label = "CEP",
                        placeholder = "Seu CEP",
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row {
                    BaseInputField(
                        value = uiState.city,
                        modifier = Modifier.weight(0.8f),
                        onValueChange = {
                            uiState.onCityChange(it)
                        },
                        label = "Cidade",
                        placeholder = "Sua cidade",
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    BaseInputField(
                        value = uiState.state,
                        modifier = Modifier.weight(0.2f),
                        onValueChange = {
                            uiState.onHouseNumberChange(it)
                        },
                        label = "UF",
                        placeholder = "UF",
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                BaseInputField(
                    value = uiState.neighborhood,
                    onValueChange = {
                        uiState.onNeighborhoodChange(it)
                    },
                    label = "Bairro",
                    placeholder = "Digite seu bairro",
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row {

                    BaseInputField(
                        value = uiState.street,
                        modifier = Modifier.weight(0.8f),
                        onValueChange = {
                            uiState.onStreetChange(it)
                        },
                        label = "Rua",
                        placeholder = "Sua rua",
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    BaseInputField(
                        modifier = Modifier.weight(0.2f).focusRequester(focusRequester),
                        onValueChange = {
                            uiState.onHouseNumberChange(it)
                        },
                        label = "Nº",
                        placeholder = "",
                    )
                }
            }
        }


        Box {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                BaseButton(
                    text = "Criar conta",
                    onClick = {
                        viewModel.createAccount()
                    },
                    modifier = Modifier.width(200.dp),
                    enabled = uiState.canRegister()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text(text = "Já tem uma conta? ", fontSize = 14.sp)
                    Text(
                        text = "Fazer login",
                        color = PrimaryColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.clickable {
                            onPopBackStack()
                        }
                    )
                }
            }
        }

    }
}