package br.senai.sp.jandira.lazycolumnromm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_product")
data class Product(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "product_name") var productName: String = "Product Name",
    var productDescription: String = "Description's product",
    var productPrice: Double = 0.0
)

