package br.com.fiap.mentora.common.button

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.fiap.mercadoverde.presentation.theme.Inter
import br.com.fiap.mercadoverde.presentation.theme.PrimaryColor

enum class ButtonType {
    SOLID,
    OUTLINED
}

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    buttonType: ButtonType = ButtonType.SOLID,
    enabled: Boolean = true,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor
//            containerColor = if (buttonType == ButtonType.OUTLINED) BackgroundDark
//            else PrimaryColor
        ),
        enabled = enabled
    ) {
        Text(
            text = text,
//            color = if (buttonType == ButtonType.OUTLINED) TextColorLight else TextContrast,
            fontFamily = Inter
        )
    }
}