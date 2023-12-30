package com.example.inventory.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventory.*
import com.example.inventory.item.LowStockFragmentDirections
import com.example.inventory.databinding.FragmentLowStockBinding

class LowStockFragment : Fragment() {

    private var _binding: FragmentLowStockBinding? = null
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
        _binding = FragmentLowStockBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ItemListAdapter {
            val action =
                LowStockFragmentDirections.actionLowStockFragmentToDashboardFragment()
            this.findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter
        //After setting the adapter, attach an observer on the allItems to
        // listen for the data changes.
        viewModel.lowStockItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        //this.findNavController().navigate(action)
    }
}