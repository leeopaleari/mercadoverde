package br.com.fiap.mercadoverde.data.source.local.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Long,

    @ColumnInfo
    var name: String,

    @ColumnInfo
    var categoria: String,

    @ColumnInfo
    var preco: Float,

    @ColumnInfo
    var imagem: Int, // TODO: Futuramente alterar para string pois será url do servidor

    @ColumnInfo
    var tipo: String = "Unidade",

    @ColumnInfo
    var quantidade: Int = 1
)