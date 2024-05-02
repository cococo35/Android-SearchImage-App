package com.android.searchimageapp.data

import com.google.gson.annotations.SerializedName

data class searchDTO(val response: SearchResponse)

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
    @SerializedName("datetime") val dateTime: String,
    @SerializedName("contents") val contents: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)
