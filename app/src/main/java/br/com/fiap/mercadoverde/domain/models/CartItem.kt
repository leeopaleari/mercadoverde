package br.com.fiap.mercadoverde.domain.models

data class CartItem(
    val product: Product,
    val quantity: Int
)