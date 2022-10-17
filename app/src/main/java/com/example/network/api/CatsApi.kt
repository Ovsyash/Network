package com.example.network.api

import com.example.network.data.cats.CatsResponse
import retrofit2.Call
import retrofit2.http.GET

interface CatsApi {

    @GET("v1/images/search?limit=10")
    fun fetchCats(): Call<CatsResponse>
}