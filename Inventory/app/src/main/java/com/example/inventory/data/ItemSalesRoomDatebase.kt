package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Item::class,Sales::class],version = 4, exportSchema = false)
abstract class ItemSalesRoomDatabase : RoomDatabase() {

    abstract fun itemSalesDao(): ItemSalesDao

    companion object {
        @Volatile
        private var INSTANCE: ItemSalesRoomDatabase? = null
        fun getDatabase(context: Context): ItemSalesRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemSalesRoomDatabase::class.java,
                    "item_sales_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}