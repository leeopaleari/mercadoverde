package br.com.fiap.mercadoverde.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mercadoverde.presentation.theme.Inter

@Composable
fun CustomTextField(
    placeholderText: String = "",
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    fontSize: TextUnit = 14.sp
) {

    var text by rememberSaveable {
        mutableStateOf("")
    }

    BasicTextField(modifier = modifier
        .background(
            Color.White,
            MaterialTheme.shapes.small,
        )
        .fillMaxWidth()
        .height(45.dp)
        .background(
            Color(0xFFFFFF),
        )
        .border(width = 1.dp, color = Color(0xFFD1D1D1), shape = RoundedCornerShape(20))
        .padding(4.dp),
        value = text,
        onValueChange = {
            text = it
        },
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = fontSize,
            fontFamily = Inter
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier.then(Modifier.height(45.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    Spacer(modifier = Modifier.width(12.dp))
                    leadingIcon()
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Box(Modifier.weight(1f)) {
                    if (text.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = Color(0xFF707070),
                            fontSize = fontSize,
                            fontFamily = Inter,
                        ),
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}