package com.example.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.network.cats.CatsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, CatsFragment.newInstance())
                .commit()

        }
    }
}