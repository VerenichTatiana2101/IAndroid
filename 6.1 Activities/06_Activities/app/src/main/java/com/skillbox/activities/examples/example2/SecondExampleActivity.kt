package com.skillbox.activities.examples.example2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.skillbox.activities.databinding.ActivitySecondExampleBinding

class SecondExampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondExampleBinding.inflate(layoutInflater)
        binding.greetings.visibility = View.VISIBLE
        setContentView(binding.root)
    }
}
