package com.example.lifecycle

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.lifecycle.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var handler = Handler(Looper.getMainLooper())

    private var timerValue = 0
    private var timeCounter = 0
    private var timerIsActive = false

    private lateinit var timerThread: Thread
    private var timerThreadStop = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            timerValue = savedInstanceState.getInt("timerValue")
            timerIsActive = savedInstanceState.getBoolean("timerIsActive")
            timeCounter = timerValue
            handler.post {
                updateTimer()
                if (timerIsActive) startTimer()
            }
        }

        handler.post {
            binding.slider.addOnChangeListener { _, value, _ ->
                timerValue = value.toInt()
                timeCounter = timerValue
                updateUI()
            }

            binding.buttonStartStop.setOnClickListener {
                if (timerIsActive) {
                    timerIsActive = false
                    stopTimerThread()
                } else {
                    startTimer()
                }
            }
        }
    }

    private fun updateUI() {
        binding.slider.isEnabled = !timerIsActive
        binding.buttonStartStop.text = if (timerIsActive) "STOP" else "START"
        binding.progressBar.max = binding.slider.value.toInt()
        binding.progressBar.progress = timeCounter
        binding.progressText.text = timerValue.toString()
    }

    private fun updateTimer() {
        binding.progressText.text = timeCounter.toString()
        binding.progressBar.progress = timeCounter
    }

    private fun startTimer() {
        timerThread = Thread {
            while (!timerThreadStop && timeCounter > 0) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                timeCounter--
                handler.post { updateTimer() }
            }
            if (timeCounter == 0) timerIsActive = false
            handler.post { finishTimer() }
        }

        timerIsActive = true
        handler.post { updateUI() }
        timerThread.start()
    }

    private fun finishTimer() {
        if (!timerIsActive) {
            Toast.makeText(this, "Timer Finished", Toast.LENGTH_SHORT).show()
        }
        timerValue = binding.slider.value.toInt()
        timeCounter = timerValue
        timerThreadStop = false
        handler.post { updateUI() }
    }

    private fun stopTimerThread() {
        timerThreadStop = true
        timerThread.interrupt()
    }

    override fun onPause() {
        super.onPause()
        try {
            stopTimerThread()
        } catch (ex: Exception) {
            Log.d("msg", "exception onPause: $ex")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("timerValue", timeCounter)
        outState.putBoolean("timerIsActive", timerIsActive)
        super.onSaveInstanceState(outState)
    }
}
