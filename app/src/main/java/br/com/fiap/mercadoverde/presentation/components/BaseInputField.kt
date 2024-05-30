package br.com.fiap.mercadoverde.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mercadoverde.presentation.theme.PrimaryColor


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BaseInputField(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    label: String,
    placeholder: String = "",
    value: String = "",
    textArea: Boolean = false,
    autoFocus: Boolean = false,
    trailingIcon: @Composable() (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
) {

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    var text by rememberSaveable(value) {
        mutableStateOf(value)
    }

    if (autoFocus) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        label = { Text(text = label, fontSize = 14.sp, color = Color(0xFF707070)) },
        placeholder = { Text(text = placeholder, fontSize = 12.sp, color = Color(0xFF707070)) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = !textArea,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color(0xFFD1D1D1),
            unfocusedContainerColor = Color(0xFFFFFFFF),
            focusedContainerColor = Color(0xFFFFFFFF),
            focusedBorderColor = PrimaryColor,
        ),
        minLines = if (textArea) 6 else 1,
        visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        shape = RoundedCornerShape(8.dp)
    )
}