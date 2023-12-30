package com.example.inventory.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat

@Entity(tableName = "item_tbl")
data class Item (
    @PrimaryKey(autoGenerate = true)
    val itemId : Int = 0,
    @NonNull @ColumnInfo(name="name")
    val itemName : String,
    @NonNull @ColumnInfo(name="price")
    val itemPrice : Double,
    @NonNull @ColumnInfo(name="quantity")
    val quantityInStock : Int

)
//extension function
fun Item.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().format(itemPrice)

/*
Kotlin data class objects have some extra benefits, the compiler
automatically generates utilities for comparing, printing and
copying such as toString(), copy(), and equals().
 */