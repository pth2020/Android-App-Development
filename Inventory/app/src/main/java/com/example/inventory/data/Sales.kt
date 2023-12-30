package com.example.inventory.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(
    foreignKeys = [ForeignKey(
        entity = Item::class,
        parentColumns = arrayOf("itemId"),
        childColumns = arrayOf("itemFKId"),
        onDelete = ForeignKey.CASCADE
    )]
)

data class Sales (@PrimaryKey(autoGenerate = true)
                  val salesId : Int = 0,
                  @NonNull @ColumnInfo(name="quantitySold")
                  val quantitySold : Int = 0,
                  @NonNull @ColumnInfo(name="dateSold")
                  val dateSold : String,
                  @NonNull @ColumnInfo(name="itemFKId")
                  val itemFKId : Int
)
//extension function
//fun Date.formatToViewDateDefaults() : String  =
  //  SimpleDateFormat("dd-mm-yyyy",Locale.getDefault()).format(this)


