package com.android.searchimageapp.data

import com.google.gson.annotations.SerializedName

//data class searchDTO(val response: SearchResponse)

data class SearchResponse(
    @SerializedName("meta") val searchMeta: Meta,
    @SerializedName("documents") val searchDocuments: List<Document>
)

data class Meta(
    @SerializedName("total_count") val count: Int,
    @SerializedName("pageable_count") val pageCount: Int,
    @SerializedName("is_end") val isEnd: Boolean
)

data class Document(
    @SerializedName("collection") val collection: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("display_sitename") val siteName: String,
    @SerializedName("doc_url") val docUrl: String,
    @SerializedName("datetime") val dateTime: String,    // Datetime
    var isSelected: Boolean = false
)
