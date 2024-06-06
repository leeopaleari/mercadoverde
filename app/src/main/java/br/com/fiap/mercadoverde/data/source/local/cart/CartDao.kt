package br.com.fiap.mercadoverde.data.source.local.cart

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    suspend fun getAll(): List<CartItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItemEntity)

    @Update
    suspend fun update(cartItem: CartItemEntity)

    @Delete
    suspend fun delete(cartItem: CartItemEntity)

    @Query("SELECT * FROM cart_items WHERE productId = :productId")
    suspend fun getById(productId: String): CartItemEntity

    @Query("DELETE FROM cart_items WHERE productId = :productId")
    suspend fun deleteById(productId: String)
}

//@Dao
//interface CartDao {
//    @Query("SELECT * FROM cart_items")
//    suspend fun getAll(): List<CartItemEntity>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(cartItem: CartItemEntity): Long
//
//    @Update
//    suspend fun update(cartItem: CartItemEntity)
//
//    @Delete
//    suspend fun delete(cartItem: CartItemEntity): Int
//
//    @Query("DELETE FROM cart_items WHERE productId = :productId")
//    suspend fun deleteById(productId: String): Int
//}