package com.android.searchimageapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.searchimageapp.data.Document
import com.android.searchimageapp.data.SelectedItems
import com.android.searchimageapp.databinding.RecyclerviewItemBinding
import com.bumptech.glide.Glide

class StorageAdapter(private val onClick: (Document) -> Unit): RecyclerView.Adapter<StorageAdapter.Holder>() {
    var data = SelectedItems.selectedItems
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageAdapter.Holder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: StorageAdapter.Holder, position: Int) {
        val currentItem = data[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            onClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class Holder(private val binding: RecyclerviewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Document) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.thumbnailUrl)
                    .into(imgItem)
                tvItemSite.text = item.siteName
                tvItemDate.text = item.dateTime.replace("T", " ").substring(0 until 19)

                if(item.isSelected) {
                    imgItemHeart.visibility = View.VISIBLE
                } else {
                    imgItemHeart.visibility = View.GONE
                }
            }
        }
    }
}