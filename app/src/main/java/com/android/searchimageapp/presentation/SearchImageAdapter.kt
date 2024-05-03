package com.android.searchimageapp.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.android.searchimageapp.data.Document
import com.android.searchimageapp.databinding.RecyclerviewItemBinding

class SearchImageAdapter(private val data: List<Document>): RecyclerView.Adapter<SearchImageAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageAdapter.Holder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: SearchImageAdapter.Holder, position: Int) {
        val currentItem = data[position]
        Log.d("SearchImageAdapter", "onBindViewHolder: position=$position, item=$currentItem")
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        Log.d("SearchImageAdapter1", "getItemCount: ${data.size}")
        return data.size
    }

    class Holder(private val binding: RecyclerviewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Document) {
            binding.apply {
                Log.d("SearchImageAdapter", "bind: imageUrl=${item.imageUrl}")
                imgItem.setImageURI(item.imageUrl.toUri())
                Log.d("SearchImageAdapter", "bind: siteName=${item.siteName}")
                tvItemSite.text = item.siteName
                Log.d("SearchImageAdapter", "bind: dateTime=${item.dateTime}")
                tvItemDate.text = item.dateTime
            }
        }
    }
}