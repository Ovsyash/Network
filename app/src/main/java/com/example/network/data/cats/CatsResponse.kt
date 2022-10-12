package com.example.network.data.cats

import com.example.network.data.cats.CatsItem
import com.google.gson.annotations.SerializedName

class CatsResponse {
    lateinit var catsItem: List<CatsItem>
}
