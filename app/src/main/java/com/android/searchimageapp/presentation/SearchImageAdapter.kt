package com.android.searchimageapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.searchimageapp.data.Document
import com.android.searchimageapp.databinding.RecyclerviewItemBinding

class SearchImageAdapter(val data: List<Document>): RecyclerView.Adapter<SearchImageAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageAdapter.Holder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: SearchImageAdapter.Holder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class Holder(private val binding: RecyclerviewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Document) {
            binding.apply {
                imgItem
            }
        }
    }
}