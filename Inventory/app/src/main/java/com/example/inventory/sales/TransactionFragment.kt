package com.example.inventory.sales

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.inventory.data.Item
import com.example.inventory.*
import com.example.inventory.databinding.FragmentTransactionBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TransactionFragment : Fragment() {

    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: TransactionFragmentArgs by navArgs()

    lateinit var item: Item


    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database
                .itemSalesDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun addNewSales(item : Item) {
        //if (isEntryValid()) {
            viewModel.addNewSales(
                binding.itemsSold.text.toString(),
                binding.salesDate.text.toString(),
                item.itemId.toString()
            )

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.itemId

        viewModel.retrieveItem(id).observe(this.viewLifecycleOwner) { selectedItem ->
            item = selectedItem
            bind(item)
        }
    }

    //add textFields when editing data
    @RequiresApi(Build.VERSION_CODES.O)
    private fun bind(item: Item) {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val current = LocalDateTime.now().format(formatter)
        binding.apply {
            itemName.setText(item.itemName, TextView.BufferType.SPANNABLE)
            salesDate.setText(current)
            itemsSold.text.toString()
            salesTransactionBtn.setOnClickListener {
                sellItem(item)
            }
        }
    }

    private fun sellItem(item: Item) {
        viewModel.sellItem(item, binding.itemsSold.text.toString().toInt())
        addNewSales(item)
        val action = TransactionFragmentDirections.actionTransactionFragment2ToItemListFragment2(

        )
        findNavController().navigate(action)

    }
}