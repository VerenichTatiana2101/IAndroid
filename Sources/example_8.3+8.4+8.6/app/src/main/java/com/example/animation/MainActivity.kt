package com.example.animation

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lottieView = findViewById<LottieAnimationView>(R.id.lottie_view)
        val progressIndicator = findViewById<LinearProgressIndicator>(R.id.progress)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            lottieView.playAnimation()
        }

        /*
        button.translationY = 400f
        button.animate().apply {
            duration = 1000
            scaleX(2f)
            scaleY(2f)
            rotation(360f)
            translationY(-10f)
            interpolator = AccelerateDecelerateInterpolator()
        }
         */

        /*
        ObjectAnimator.ofFloat(
            button,
            View.ROTATION,
            0f,
            720f
        ).apply {
            duration = 4000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
         */

        /*
        ObjectAnimator.ofArgb(button,
            "textColor",
            Color.parseColor("#FFFF0000"),
            Color.parseColor("#FF0000FF")
        ).apply {
            duration = 4000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = 4
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
         */

        (AnimatorInflater.loadAnimator(this, R.animator.custom_animation) as AnimatorSet)
            .apply {
                setTarget(button)
                start()
            }

        lottieView.addAnimatorUpdateListener {
            progressIndicator.progress = ((it.animatedValue as Float) * 100).roundToInt()
            Log.d("LOTTIE", "${it.animatedValue}")
        }

    }
}

