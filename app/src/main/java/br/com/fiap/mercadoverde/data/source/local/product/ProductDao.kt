package br.com.fiap.mercadoverde.data.source.local.product

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Insert
    suspend fun insert(productEntity: ProductEntity): Long

    @Delete
    suspend fun delete(productEntity: ProductEntity): Int

    @Query("SELECT * FROM tb_product")
    suspend fun getAll(): List<ProductEntity>

    @Query("SELECT COUNT(*) FROM tb_product")
    suspend fun getCartSize(): Int

    @Update
    suspend fun update(productEntity: ProductEntity): Int

    @Query("SELECT * FROM tb_product WHERE id = :productId")
    suspend fun findById(productId: Long): ProductEntity

    @Query("DELETE FROM tb_product")
    suspend fun clearItems()
}