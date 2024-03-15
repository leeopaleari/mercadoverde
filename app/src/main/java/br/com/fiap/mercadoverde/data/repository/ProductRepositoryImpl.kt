package br.com.fiap.mercadoverde.data.repository

import br.com.fiap.mercadoverde.data.data_source.ProductDao
import br.com.fiap.mercadoverde.domain.models.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productDao: ProductDao) {
    suspend fun insert(product: Product): Long {
        return productDao.insert(product)
    }

    suspend fun delete(product: Product): Int {
        return productDao.delete(product)
    }

    suspend fun getAll(): List<Product> {
        return productDao.getAll()
    }

    suspend fun increaseItemQty(product: Product): Int {
        return product.let {
            it.quantidade++
            return productDao.update(product)
        }
    }

    suspend fun decreaseItemQty(product: Product): Int {
        product.quantidade--
        return productDao.update(product)
    }

}