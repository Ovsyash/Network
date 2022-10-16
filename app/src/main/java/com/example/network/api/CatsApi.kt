package com.example.network.api

import com.example.network.data.cats.CatsItem
import retrofit2.Call
import retrofit2.http.GET

interface CatsApi {

    @GET("v1/images/search?limit=50&api_key=live_r8Ze1xzepcqQ368TvPal5fYXQyFcF67Inp0i6RFkcPcWbXYvakEpAlx3oXjIqlXE")
    fun fetchCats(): Call<List<CatsItem>>
}