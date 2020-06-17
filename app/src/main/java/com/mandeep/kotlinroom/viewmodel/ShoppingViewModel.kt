package com.mandeep.kotlinroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mandeep.kotlinroom.db.ShoppingDao
import com.mandeep.kotlinroom.db.ShoppingDatabase
import com.mandeep.kotlinroom.db.entities.ShoppingItem
import com.mandeep.kotlinroom.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ShoppingViewModel(application: Application) : AndroidViewModel(application) {

    private val shoppingRepository: ShoppingRepository
    val getAllItems: LiveData<List<ShoppingItem>>

    init {
        val shoppingDao: ShoppingDao = ShoppingDatabase.getDatabase(application).getDao()
        shoppingRepository = ShoppingRepository(shoppingDao)
        getAllItems = shoppingRepository.getAllItems()
    }

    fun addItem(item: ShoppingItem) = CoroutineScope(IO).launch { shoppingRepository.upsert(item) }

    fun deleteItem(item: ShoppingItem) =
        CoroutineScope(IO).launch { shoppingRepository.delete(item) }

}