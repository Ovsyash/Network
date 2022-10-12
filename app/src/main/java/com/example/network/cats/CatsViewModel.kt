package com.example.network.cats

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.network.api.Cats
import com.example.network.data.cats.CatsItem

class CatsViewModel : ViewModel() {

    val catsItemLiveData: LiveData<List<CatsItem>>

    init {
        catsItemLiveData = Cats().fetchNews()
    }
}