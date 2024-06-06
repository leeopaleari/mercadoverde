package br.com.fiap.mercadoverde.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.fiap.mercadoverde.data.source.local.cart.CartDao
import br.com.fiap.mercadoverde.data.source.local.cart.CartItemEntity
import br.com.fiap.mercadoverde.data.source.local.product.ProductDao
import br.com.fiap.mercadoverde.data.source.local.product.ProductEntity

@Database(
    entities = [ProductEntity::class, CartItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    abstract fun cartDao(): CartDao

}