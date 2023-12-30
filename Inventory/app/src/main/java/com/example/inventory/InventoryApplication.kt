package com.example.inventory

import android.app.Application
import com.example.inventory.data.ItemSalesRoomDatabase

class InventoryApplication : Application() {
    val database : ItemSalesRoomDatabase by lazy {
        ItemSalesRoomDatabase.getDatabase(this)
    }
}