package com.example.homework

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isInvisible
import com.example.homework.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object {
        const val COUNT_PASSENGER: Int = 50
    }

    private var cnt: Int = 0
    private var cntPlaces = COUNT_PASSENGER


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater) //добавили
        setContentView(binding.root)
        binding.reduceView.isEnabled = false //состояние пустого

        //binding.helloView.text = getText(R.string.helloText)
        //binding.exitView.text = getText(R.string.exitText)
        //или из другого класса
        //binding.helloView.text = binding.helloView.context.getText(R.string.helloText)
        //binding.addView.text = binding.addView.context.getText(R.string.exitText)


        binding.reduceView.setOnClickListener {
            binding.helloView.setTextColor(Color.BLUE)
            binding.addView.isEnabled = true
            binding.restartView.visibility = View.INVISIBLE
            if (cnt > 0) {  //перенести в метод reduceCounter
                cnt--
                binding.counterView.text = cnt.toString()
                cntPlaces++
                if (cnt == 0) {
                    binding.reduceView.isEnabled = false  //состояние пустого
                    binding.helloView.setTextColor(Color.GREEN)
                    binding.helloView.text = getString(R.string.empty)
                } else {
                    binding.helloView.text = getString(R.string.noEmpty) + " "+ cntPlaces
                }
            }
            else binding.reduceView.isEnabled = true

        }

        binding.addView.setOnClickListener {
            binding.reduceView.isEnabled = true
            binding.helloView.setTextColor(Color.BLUE)
            if (cnt < COUNT_PASSENGER) {
                cnt++   //перенести в addCounter
                binding.counterView.text = cnt.toString()
                cntPlaces--
                if (cntPlaces == 0) {   //перенести в fullCounter состояние полного
                    binding.addView.isEnabled = false
                    binding.helloView.text = getString(R.string.full)
                    binding.helloView.setTextColor(Color.RED)
                    binding.restartView.visibility = View.VISIBLE
                    binding.restartView.setOnClickListener {
                        cnt = 0  //состояние пустого
                        cntPlaces = COUNT_PASSENGER
                        binding.helloView.setTextColor(Color.GREEN)
                        binding.restartView.visibility = View.INVISIBLE  //перенести в emptyCounter, добавить цвет
                        binding.helloView.text = getString(R.string.empty)
                        binding.counterView.text = cnt.toString()
                        binding.addView.isEnabled = true
                        binding.reduceView.isEnabled = false
                    }
                } else {
                    binding.helloView.text = getString(R.string.noEmpty) + " " + cntPlaces
                }
            }
            else binding.addView.isEnabled = false
        }


        /*
        setContentView(R.layout.activity_main)
        val textView : TextView = findViewById(R.id.helloView)
        textView.text = "Hello My Friend!"

        val exitText : TextView = findViewById(R.id.exitView)
        exitText.text = "EXIT"
        // эту часть заменяем */
    }
}