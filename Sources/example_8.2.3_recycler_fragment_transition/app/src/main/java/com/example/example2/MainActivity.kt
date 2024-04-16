package com.example.example2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, FirstFragment(), FIRST_FRAGMENT)
                addToBackStack(FIRST_FRAGMENT)
                commit()
            }
        }
    }

    fun showSecondFragment(image: ImageView, textView: TextView, id: Int) {
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(SECOND_FRAGMENT)
            setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            replace(R.id.container, SecondFragment.newInstance(id), SECOND_FRAGMENT)
            commit()
        }
    }

    companion object {
        private const val FIRST_FRAGMENT = "FIRST_FRAGMENT"
        private const val SECOND_FRAGMENT = "FIRST_FRAGMENT"
    }
}