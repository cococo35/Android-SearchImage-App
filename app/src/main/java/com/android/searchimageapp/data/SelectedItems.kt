package com.android.searchimageapp.data

import android.util.Log

object SelectedItems {
    private var _selectedItems = listOf<Document>().toMutableList()
    val selectedItems: List<Document> get() = _selectedItems

    fun addItem(data: Document) {
        _selectedItems.add(data)
        Log.d("selectedItems_add", "$_selectedItems" )
        Log.d("selectedItems_add", "$selectedItems" )
    }

    fun removeItem(data: Document) {
        _selectedItems = _selectedItems.filterNot {
            it.thumbnailUrl == data.thumbnailUrl
        }.toMutableList()
        Log.d("selectedItems_remove", "$_selectedItems")
        Log.d("selectedItems_remove", "$selectedItems")
    }
}