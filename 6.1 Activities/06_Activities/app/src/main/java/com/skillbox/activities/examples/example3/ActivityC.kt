package com.skillbox.activities.examples.example3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skillbox.activities.databinding.ActivityCBinding

// значения launchMode для Activity можно посмотреть в файле AndroidManifest
// кнопки открывают соответсвующие Activity
class ActivityC : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCBinding.inflate(layoutInflater)

        binding.buttonA.setOnClickListener {
            val intent = Intent(this, ActivityA::class.java)
            startActivity(intent)
        }
        binding.buttonB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            startActivity(intent)
        }
        binding.buttonD.setOnClickListener {
            val intent = Intent(this, ActivityD::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}
