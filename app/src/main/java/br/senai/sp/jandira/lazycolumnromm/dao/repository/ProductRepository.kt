package br.senai.sp.jandira.lazycolumnromm.dao.repository

import android.content.Context
import br.senai.sp.jandira.lazycolumnromm.dao.ProductDb
import br.senai.sp.jandira.lazycolumnromm.model.Product

class ProductRepository(context: Context) {

    private val db = ProductDb.getDatabase(context).productDao()

    //criar metodos ou atributos estaticos
    fun getProductsList(): List<Product> {
        return db.getAll()
    }

    fun save(product: Product): Int {
        return db.save(product).toInt()
    }

}

