package com.skillbox.activities.examples.example3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skillbox.activities.databinding.ActivityABinding

// стартовая Activity для примера с демонстрацией работы back stack и различных launchMode
// значения launchMode для Activity можно посмотреть в файле AndroidManifest
// кнопки открывают соответсвующие Activity
class ActivityA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityABinding.inflate(layoutInflater)

        binding.buttonB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            startActivity(intent)
        }

        binding.buttonD.setOnClickListener {
            val intent = Intent(this, ActivityD::class.java)
            startActivity(intent)
        }

        binding.settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}
