package com.skillbox.activities.examples.example3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.skillbox.activities.R
import com.skillbox.activities.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingsBinding.inflate(layoutInflater)

        // установка обработчика клика для кнопки
        binding.save.setOnClickListener {
            // имитация работы по сохранению настроек
            save()
            // завершение работы Activity
            finish()
        }

        setContentView(binding.root)
    }

    private fun save() {
        Toast.makeText(this, "Saving process", Toast.LENGTH_SHORT).show()
    }
}
