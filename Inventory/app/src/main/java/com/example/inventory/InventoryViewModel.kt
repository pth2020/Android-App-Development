package com.example.inventory

import androidx.lifecycle.*
import com.example.inventory.data.*
import kotlinx.coroutines.*


class InventoryViewModel(private val itemSalesDao : ItemSalesDao) : ViewModel() {

    val allItems: LiveData<List<Item>> = itemSalesDao.getItems().asLiveData()

    //val allItemSales : LiveData<List<ItemSales>> = itemSalesDao.getItemSalesInfo().asLiveData()
    val allItemSales : LiveData<List<ItemSales>> = itemSalesDao.getItemSalesByDate().asLiveData()
    val lowStockItems : LiveData<List<Item>> = itemSalesDao.getLowStockItems().asLiveData()

    //Methods to insert an item to the database

    //When the isEntryValid() method in AddFragment is called it calls this method in return
    //This method validates if the variables are non-null
    fun isEntryValid(itemName: String, itemPrice: String, itemCount: String): Boolean {
        if (itemName.isBlank() || itemPrice.isBlank() || itemCount.isBlank()) {
            return false
        }
        return true
    }

    fun addNewItem(itemName: String, itemPrice: String, itemCount: String) {
        val newItem = getNewItemEntry(itemName, itemPrice, itemCount)
        insertItem(newItem)
    }

    private fun getNewItemEntry(itemName : String, itemPrice : String, itemCount : String): Item {
        return Item(
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantityInStock = itemCount.toInt()
        )
    }

    private fun insertItem(item : Item){
        viewModelScope.launch {
            itemSalesDao.insertItem(item)
        }
    }

    private fun insertSales(sales : Sales){
        viewModelScope.launch{
            itemSalesDao.insertSales(sales)
        }

    }


    fun retrieveItem(id : Int) : LiveData<Item> {
        return itemSalesDao.getItem(id).asLiveData()
    }

    fun deleteItem(item : Item) {
        viewModelScope.launch {
            itemSalesDao.deleteItem(item)
        }
    }

    private fun updateItem(item : Item) {
        viewModelScope.launch {
            itemSalesDao.updateItem(item)
        }
    }

    private fun updateSelectedItems(item : Item){
        viewModelScope.launch {
            itemSalesDao.updateItem(item)
        }
    }

    //The copy() function is provided by default to all the instances
    // of data classes. This function is used to copy an object for changing
    // some of its properties, but keeping the rest of the properties unchanged.
    fun sellItem(item : Item, qtySold : Int){

       if(item.quantityInStock >= qtySold){
          val newItem = item.copy(quantityInStock = item.quantityInStock - qtySold)
          updateItem(newItem)
       }
        else {

       }
    }

    private fun getUpdatedItemEntry(
        itemId: Int,
        itemName: String,
        itemPrice: String,
        itemCount: String
    ): Item {
        return Item(
            itemId = itemId,
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantityInStock = itemCount.toInt()
        )
    }

    fun updateItem(
        itemId: Int,
        itemName: String,
        itemPrice: String,
        itemCount: String
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, itemName, itemPrice, itemCount)
        updateItem(updatedItem)
    }

    fun isStockAvailable(item : Item) : Boolean {
        return (item.quantityInStock > 0)
    }



    private fun getNewSalesEntry(quantitySold: String,salesDate: String, itemFkId : String) : Sales {
        return Sales(
            quantitySold = quantitySold.toInt(),
            dateSold = salesDate,
            itemFKId = itemFkId.toInt()
            )
    }

    fun addNewSales(quantitySold: String, salesDate : String, itemFKId : String){
        val newSales = getNewSalesEntry(quantitySold,salesDate,itemFKId)
        insertSales(newSales)
    }



    fun twoPair() : Pair<Int,Int> =  0 to 0

    //get all products/items
    fun countAllItems() : Pair<Int,Int> {
        var totalItems: Int = 0
        var totalLowStockItems : Int = 0
        var pair : Pair<Int,Int> = twoPair()
        val job = GlobalScope.launch {
            totalItems = itemSalesDao.getTotalItems()
            totalLowStockItems = itemSalesDao.getTotalLowStockItems()
            pair = totalItems to totalLowStockItems
        }
        runBlocking {
            job.join()
        }

        return pair
    }
}

class InventoryViewModelFactory (private val itemDao : ItemSalesDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}