package br.com.fiap.mercadoverde.data.repository

import br.com.fiap.mercadoverde.data.source.local.cart.CartDao
import br.com.fiap.mercadoverde.data.source.local.cart.CartItemEntity
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val cartDao: CartDao) {

    suspend fun insert(cartItemEntity: CartItemEntity) {
        return cartDao.insert(cartItemEntity)
    }

    suspend fun delete(cartItemEntity: CartItemEntity) {
        return cartDao.delete(cartItemEntity)
    }

    suspend fun getAll(): List<CartItemEntity> {
        return cartDao.getAll()
    }

    suspend fun getById(productId: String): CartItemEntity {
        return cartDao.getById(productId)
    }

    suspend fun increaseItemQty(productId: String): Int {
        val product = cartDao.getById(productId)

        if (product != null) {
            product.quantity++
            return cartDao.update(product)
        }

        return -1
    }

    suspend fun decreaseItemQty(productId: String): Int {
        val product = cartDao.getById(productId)

        if (product != null) {
            product.quantity--
            return cartDao.update(product)
        }

        return -1
    }

    suspend fun clearCartItems() {
        cartDao.clearItems()
    }

}