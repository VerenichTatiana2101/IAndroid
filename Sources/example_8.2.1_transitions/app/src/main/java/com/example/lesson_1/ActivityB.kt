package com.example.lesson_1

import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity

import androidx.appcompat.app.AppCompatActivity

class ActivityB : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(window) {
            enterTransition = Explode()
            exitTransition = Slide(Gravity.RIGHT)
        }
        setContentView(R.layout.activity_b)
    }
}