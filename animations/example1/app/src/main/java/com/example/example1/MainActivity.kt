package com.example.example1

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.example1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // использование binding утилиты для создания View
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val options = ActivityOptions.makeSceneTransitionAnimation(this)

        binding.button.setOnClickListener{
            startActivity(Intent(this, ActivityB::class.java), options.toBundle()
            )
        }
        setContentView(binding.root)
    }
}