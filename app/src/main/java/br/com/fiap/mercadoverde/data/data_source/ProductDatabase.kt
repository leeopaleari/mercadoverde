package br.com.fiap.mercadoverde.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.fiap.mercadoverde.domain.models.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}