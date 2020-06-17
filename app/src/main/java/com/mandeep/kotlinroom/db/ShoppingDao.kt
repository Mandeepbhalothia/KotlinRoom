package com.mandeep.kotlinroom.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mandeep.kotlinroom.db.entities.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * from shoppingitems")
    fun getAllItems(): LiveData<List<ShoppingItem>>
}