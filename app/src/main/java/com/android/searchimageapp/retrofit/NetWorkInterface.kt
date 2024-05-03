package com.android.searchimageapp.retrofit

import com.android.searchimageapp.data.searchDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface NetWorkInterface {
    @Headers("Authorization: KakaoAK 087e584018c0f7bfd2c5e694c504335c")
    @GET("/v2/search/image")
    suspend fun getSearch(@QueryMap param: HashMap<String, String>): searchDTO
}