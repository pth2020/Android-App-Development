package com.example.inventory.sales

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.data.ItemSales
import com.example.inventory.databinding.SalesByDateListBinding
import com.example.inventory.databinding.SalesListBinding

class SalesAdapter(private val onSalesClicked: (ItemSales) -> Unit) :
     ListAdapter<ItemSales, SalesAdapter.SalesViewHolder>(DiffCallback) {

    class SalesViewHolder(private var binding : SalesByDateListBinding)://SalesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemSales : ItemSales) {
            binding.apply {
                //salesDate.text = itemSales.dateSold.toString()
                salesByDate.text = itemSales.dateSold
                //itemName.text = itemSales.name.toString()
                itemNameByDate.text = itemSales.name
                //quantitySold.text = itemSales.quantitySold.toString()
                qtySoldByDate.text = itemSales.quantitySold.toString()


            }
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, position: Int) : SalesViewHolder {
        return SalesViewHolder(
            SalesByDateListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder : SalesViewHolder, position : Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onSalesClicked(current)
        }

        holder.bind(current)
        //alternating colours
        //if(position %2 == 1)
        //{
          //  holder.itemView.setBackgroundColor(Color.parseColor("#EBF4FA"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
       // }
        //else
        //{
          //  holder.itemView.setBackgroundColor(Color.parseColor("#E5E4E2"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        //}


    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ItemSales>() {
            override fun areItemsTheSame(oldItem: ItemSales, newItem: ItemSales): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemSales, newItem: ItemSales): Boolean {
                return oldItem.salesId == newItem.salesId
            }
        }
    }

}