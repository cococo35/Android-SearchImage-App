package com.android.searchimageapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.searchimageapp.data.Document
import com.android.searchimageapp.databinding.RecyclerviewItemBinding
import com.bumptech.glide.Glide

class SearchImageAdapter(private val onClick:(Document) -> Unit): RecyclerView.Adapter<SearchImageAdapter.Holder>() {
    lateinit var data: List<Document>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageAdapter.Holder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: SearchImageAdapter.Holder, position: Int) {
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