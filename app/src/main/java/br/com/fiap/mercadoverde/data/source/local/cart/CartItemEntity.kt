package br.com.fiap.mercadoverde.data.source.local.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo
    val productId: String,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val image: String,

    @ColumnInfo
    val price: Int,

    @ColumnInfo
    val quantity: Int
)