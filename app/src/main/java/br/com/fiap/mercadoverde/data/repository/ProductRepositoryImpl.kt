package br.com.fiap.mercadoverde.data.repository

import br.com.fiap.mercadoverde.data.source.local.product.ProductDao
import br.com.fiap.mercadoverde.data.source.local.product.ProductEntity
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productDao: ProductDao) {
    suspend fun insert(productEntity: ProductEntity): Long {
        return productDao.insert(productEntity)
    }

    suspend fun delete(productEntity: ProductEntity): Int {
        return productDao.delete(productEntity)
    }

    suspend fun getAll(): List<ProductEntity> {
        return productDao.getAll()
    }

    suspend fun findById(productId: Long): ProductEntity {
        return productDao.findById(productId)
    }

    suspend fun increaseItemQty(productId: Long): Int {
        val product = productDao.findById(productId)

        if (product != null) {
            product.quantidade++
            return productDao.update(product)
        }

        return -1
    }

    suspend fun decreaseItemQty(productId: Long): Int {
        val product = productDao.findById(productId)

        if (product != null) {
            product.quantidade--
            return productDao.update(product)
        }

        return -1
    }

    suspend fun clearCartItems() {
        productDao.clearItems()
    }
}