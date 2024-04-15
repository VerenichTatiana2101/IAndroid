package com.skillbox.activities.examples.example3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skillbox.activities.databinding.ActivityDBinding

// значения launchMode для Activity можно посмотреть в файле AndroidManifest
// кнопки открывают соответсвующие Activity
class ActivityD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityDBinding.inflate(layoutInflater)

        binding.buttonD.setOnClickListener {
            val intent = Intent(this, ActivityD::class.java)
            startActivity(intent)
        }

        binding.buttonA.setOnClickListener {
            val intent = Intent(this, ActivityA::class.java)
            startActivity(intent)
        }

        binding.buttonC.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}
