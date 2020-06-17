package com.mandeep.kotlinroom.repositories

import com.mandeep.kotlinroom.db.ShoppingDao
import com.mandeep.kotlinroom.db.entities.ShoppingItem

class ShoppingRepository(private val dao: ShoppingDao) {

    suspend fun upsert(item: ShoppingItem) = dao.upsert(item)

    suspend fun delete(item: ShoppingItem) = dao.delete(item)

    fun getAllItems() = dao.getAllItems()

}