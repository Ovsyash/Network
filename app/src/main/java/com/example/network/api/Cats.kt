package com.example.network.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.network.data.cats.CatsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "Cats"

class Cats {

    private val catsApi: CatsApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        catsApi = retrofit.create(CatsApi::class.java)
    }

    fun fetchNews() : LiveData<List<CatsItem>> {
        val responseLiveData: MutableLiveData<List<CatsItem>> = MutableLiveData()
        val catsRequest: Call<com.example.network.data.cats.CatsResponse> = catsApi.fetchCats()

        catsRequest.enqueue(object : Callback<com.example.network.data.cats.CatsResponse> {

            override fun onFailure(call: Call<com.example.network.data.cats.CatsResponse>, t: Throwable) {
                Log.e(TAG, "Failed to news", t)
            }

            override fun onResponse(call: Call<com.example.network.data.cats.CatsResponse>, response: Response<com.example.network.data.cats.CatsResponse>) {
                Log.d(TAG, "Response received")
                val catsResponse: com.example.network.data.cats.CatsResponse? = response.body()
                val catsItems: List<CatsItem> = catsResponse?.catsItem
                    ?: mutableListOf()

                responseLiveData.value = catsItems
            }
        })

        return responseLiveData
    }
}

