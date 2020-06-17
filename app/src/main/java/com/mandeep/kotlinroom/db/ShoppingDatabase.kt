package com.mandeep.kotlinroom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mandeep.kotlinroom.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getDao(): ShoppingDao

    companion object {

        @Volatile // volatile is for reflect changes to all threads
        private var instance: ShoppingDatabase? = null

        /*private val LOCK = Any()

        // this method will called when we use ShoppingDatabase()
        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
            instance
                ?: createDatabase(
                    context
                ).also {
                instance = it
            }
        }*/

        fun getDatabase(context: Context): ShoppingDatabase {

            var tempInstance = instance

            if (tempInstance != null) // return previous instance if it is not null
                return tempInstance

            // create instance if it is null
            synchronized(this) {
                tempInstance = createDatabase(context)
                instance = tempInstance

                return tempInstance!!
            }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                "ShoppingDb"
            ).build()

    }

}