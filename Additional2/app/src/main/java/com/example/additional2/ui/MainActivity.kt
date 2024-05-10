package com.example.additional2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.additional2.ui.model.MainFragment
import com.example.additional2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

    }
}