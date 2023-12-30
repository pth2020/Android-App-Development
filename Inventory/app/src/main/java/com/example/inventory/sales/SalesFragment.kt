package com.example.inventory.sales

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventory.InventoryApplication
import com.example.inventory.InventoryViewModel
import com.example.inventory.InventoryViewModelFactory
import com.example.inventory.databinding.FragmentSalesBinding

class SalesFragment : Fragment() {

    private var _binding: FragmentSalesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemSalesDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSalesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SalesAdapter {
            //val action = ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(it.salesId)
            //this.findNavController().navigate(action)
        }
        binding.salesRecyclerView.adapter = adapter
        //After setting the adapter, attach an observer on the allSales to
        // listen to the data changes.
        viewModel.allItemSales.observe(this.viewLifecycleOwner) { itemsales ->
            itemsales.let {
                adapter.submitList(it)
            }
        }
        binding.salesRecyclerView.layoutManager = LinearLayoutManager(this.context)

    }


}