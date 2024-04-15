package com.skillbox.activities.examples.example3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skillbox.activities.databinding.ActivityBBinding

// значения launchMode для Activity можно посмотреть в файле AndroidManifest
// кнопки открывают соответсвующие Activity
class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBBinding.inflate(layoutInflater)

        binding.buttonC.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java)
            startActivity(intent)
        }
        binding.buttonD.setOnClickListener {
            val intent = Intent(this, ActivityD::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}
