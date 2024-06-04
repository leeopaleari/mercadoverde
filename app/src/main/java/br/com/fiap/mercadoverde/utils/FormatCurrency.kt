package br.com.fiap.mercadoverde.utils

import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(value: Double): String {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return format.format(value)
}