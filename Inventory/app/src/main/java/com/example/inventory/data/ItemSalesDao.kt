package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemSalesDao {

    //SQL commands for Item table

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM item_tbl WHERE itemId = :itemId")
    fun getItem(itemId: Int): Flow<Item>

    @Query("SELECT * FROM item_tbl ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>

    @Query("SELECT COUNT (*) From item_tbl")
    suspend fun getTotalItems(): Int

    @Query("SELECT * FROM item_tbl WHERE quantity < '5'")
    fun getLowStockItems(): Flow<List<Item>>

    @Query("SELECT COUNT (quantity) From item_tbl WHERE quantity < '5'")
    suspend fun getTotalLowStockItems(): Int

    //SQL commands for Sales table

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSales(sales : Sales)

    @Query("SELECT * FROM item_tbl INNER JOIN Sales ON " +
            "itemId = itemFKId " )
    fun getItemSalesInfo() : Flow<List<ItemSales>>

    @Query("SELECT * FROM item_tbl INNER JOIN Sales ON " +
            "itemId = itemFKId " )
    fun getItemSalesByDate() : Flow<List<ItemSales>>

    @Query("SELECT * FROM Sales ")
    fun getSales(): Flow<List<Sales>>

}