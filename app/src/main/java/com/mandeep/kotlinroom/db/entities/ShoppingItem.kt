package com.mandeep.kotlinroom.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShoppingItems")
data class ShoppingItem(
    @ColumnInfo(name = "item_name")
    var name: String,
    @ColumnInfo(name = "item_number")
    var number: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}