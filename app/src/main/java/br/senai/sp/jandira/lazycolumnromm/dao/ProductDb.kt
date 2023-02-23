package br.senai.sp.jandira.lazycolumnromm.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandira.lazycolumnromm.model.Product

@Database(entities = [Product::class], version = 1 )
abstract class ProductDb: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        private lateinit var instance: ProductDb

        fun getDatabase(context: Context): ProductDb{

            //Verificar se a instancia foi criada
            if (!::instance.isInitialized){
              instance = Room
                  .databaseBuilder(
                      context,
                      ProductDb::class.java,
                    "db_product"
                  )
                  .allowMainThreadQueries()
                  .fallbackToDestructiveMigration()
                  .build()
            }
            return instance

        }
    }
}