package com.example.homework2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.postAfter.setAddToBag("верхняя строчка, настроенная из кода")
        binding.postAfter.setPrice("нижняя строчка, настроенная из кода")
    }
}