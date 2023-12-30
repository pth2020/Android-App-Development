package com.example.inventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.inventory.data.Item
import com.example.inventory.databinding.FragmentDashboardBinding
import com.example.inventory.databinding.FragmentItemListBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
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
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var (firstInt,secondInt) = countItems()

        binding.textViewProduct.text = firstInt.toString()
        binding.textViewLowStock.text = secondInt.toString()

        binding.cardViewProducts.setOnClickListener {
            val action1 = DashboardFragmentDirections.actionDashboardFragmentToItemListFragment(

            )
            this.findNavController().navigate(action1)
        }
        binding.cardViewLowStock.setOnClickListener{
            val action2 = DashboardFragmentDirections.actionDashboardFragmentToLowStockFragment()
            this.findNavController().navigate(action2)
        }

        binding.cardViewReport.setOnClickListener {
            val action3 = DashboardFragmentDirections.actionDashboardFragmentToSalesFragment()
            this.findNavController().navigate(action3)
        }
    }
    fun countItems() : Pair<Int,Int> {
        return viewModel.countAllItems()
    }

}