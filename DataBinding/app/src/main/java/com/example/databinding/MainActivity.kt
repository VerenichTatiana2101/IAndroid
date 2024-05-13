package com.example.databinding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.ui.main.Helper
import com.example.databinding.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* вариант 1
        val pet = Pet(name = "Murka", age = 5, weight = 2.3, false)
        */

        /* так делать нельзя
        binding.pet = pet

        lifecycleScope.launch {
            delay(3_000)
            //неправильный код
            pet.isVisible = true //если напрямую binding.pet выпадет с ошибкой и val
            binding.invalidateAll() //без этого не работает
        }*/

        /* использование list
        binding.pets = listOf(
            pet,
            Pet("Pinki", 2, 1.3, true)
        )*/

        /* использование map
        binding.pets = mapOf(
            "pet1" to pet,
            "pet2" to Pet("Pink", 2, 1.3, true)
        )*/

        // инициализируем
        //binding.helper = Helper()


    }


}