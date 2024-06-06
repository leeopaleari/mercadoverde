package br.com.fiap.mercadoverde.domain.models

data class Product(
    val id: String,
    val name: String,
    val categoryId: String,
    val price: Int,
    val image: String,
    val createdAt: String,
    val updatedAt: String,
    val category: ProductCategory
) {
    val priceMonetary: Double
        get() = price / 100.0
}

data class ProductCategory(
    val id: String,
    val name: String,
    val createdAt: String,
    val image: String
)