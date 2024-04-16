package com.example.lesson_1

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ActivityA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        val button = findViewById<Button>(R.id.start_new_activity_button)
        val options = ActivityOptions.makeSceneTransitionAnimation(this)

        button.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ActivityB::class.java
                ),
                options.toBundle()
            )
        }
    }
}