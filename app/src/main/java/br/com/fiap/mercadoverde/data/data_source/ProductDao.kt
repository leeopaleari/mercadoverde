package br.com.fiap.mercadoverde.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.mercadoverde.domain.models.Product

@Dao
interface ProductDao {
    @Insert
    suspend fun insert(product: Product): Long

    @Delete
    suspend fun delete(product: Product): Int

    @Query("SELECT * FROM tb_product")
    suspend fun getAll(): List<Product>

    @Query("SELECT COUNT(*) FROM tb_product")
    suspend fun getCartSize(): Int

    @Update
    suspend fun update(product: Product): Int

    @Query("SELECT * FROM tb_product WHERE id = :productId")
    suspend fun findById(productId: Long): Product

    @Query("DELETE FROM tb_product")
    suspend fun clearItems()
}