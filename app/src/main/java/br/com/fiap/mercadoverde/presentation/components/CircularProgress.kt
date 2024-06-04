package br.com.fiap.mercadoverde.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun MyCircularProgress(
    text: String,
    showBackground: Boolean = true
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = if (showBackground) Color(0x80222222) else Color.Transparent)
            .zIndex(2f),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(80.dp)
        )
        Text(text, modifier = Modifier.offset(y = 55.dp))
    }
}