package com.skillbox.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.skillbox.activities.databinding.ActivityMainBinding
import com.skillbox.activities.examples.example2.SecondExampleActivity
import com.skillbox.activities.examples.example3.ActivityA
import com.skillbox.activities.examples.example4.RequestCameraActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // использование binding утилиты для создания View
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // устанавка обработчиков клика для кнопок
        // обращение к кнопкам происходит с помощью binding'a
        binding.example2.setOnClickListener {
            // создание объекта Intent и передача информации об Activity,
            // которую необходимо открыть
            val intent = Intent(this, SecondExampleActivity::class.java)
            // запуск Activity
            startActivity(intent)
        }
        binding.example3.setOnClickListener {
            val intent = Intent(this, ActivityA::class.java)
            startActivity(intent)
        }
        binding.example4.setOnClickListener {
            val intent = Intent(this, RequestCameraActivity::class.java)
            startActivity(intent)
        }

        // установка content view Activity с помощью созданного binding'а
        setContentView(binding.root)
    }

    //standart: A-B-C-B-D-D-D
    //
    //singleTop: A-B-C-A-D-A
    //
    //singleTask: A-B-C-D-D-D-C
}
