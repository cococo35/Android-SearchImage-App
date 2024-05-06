package com.android.searchimageapp.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.android.searchimageapp.data.Document
import com.android.searchimageapp.data.SelectedItems
import com.android.searchimageapp.databinding.FragmentStorageBinding
import com.android.searchimageapp.presentation.adapter.StorageAdapter


class StorageFragment : Fragment() {
    private lateinit var _binding: FragmentStorageBinding
    private val binding get() = _binding
    private val storageAdapter by lazy { StorageAdapter{selectedItem -> adapterOnClick(selectedItem)} }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStorageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewStorage.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = storageAdapter
        }
    }
    
    @SuppressLint("NotifyDataSetChanged")
    private fun adapterOnClick(selectedItem: Document) {
        val index = storageAdapter.data.indexOf(selectedItem)
        if (index != -1) {
            val updatedItems = storageAdapter.data.toMutableList()
            updatedItems[index] = selectedItem.copy(isSelected = !selectedItem.isSelected)
            storageAdapter.data = updatedItems
            storageAdapter.notifyDataSetChanged()

            if(storageAdapter.data[index].isSelected) {
                SelectedItems.addItem(storageAdapter.data[index])
            }
            else {
                SelectedItems.removeItem(storageAdapter.data[index])
            }
        }
    }
}