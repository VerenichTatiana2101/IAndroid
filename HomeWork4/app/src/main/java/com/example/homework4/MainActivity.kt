package com.example.homework4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random
import com.example.homework4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val randomProgress = Random.nextInt(101)
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.pointsCount.text = "$randomProgress/100"
        binding.progress.progress = randomProgress
        binding.radioGroup.setOnCheckedChangeListener { _, buttonId ->
            when (buttonId) {
                R.id.radioButtonOne -> showSnackbar(R.string.m)
                R.id.radioButtonTwo -> showSnackbar(R.string.f)
            }
            dataIsComplete()
        }


        binding.phone.doOnTextChanged { text, _, _, _ ->
            if (isPhoneValid(text) || text.isNullOrEmpty()) binding.textInputPhone.isErrorEnabled = false
            else {
                binding.textInputPhone.error = getString(R.string.error_message)
                binding.textInputPhone.isErrorEnabled = true
            }
            dataIsComplete()
        }

        binding.switchNotice.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                binding.checkbox.checkedState = MaterialCheckBox.STATE_UNCHECKED
                binding.checkboxNews.checkedState = MaterialCheckBox.STATE_UNCHECKED
                binding.checkbox.isEnabled = false
                binding.checkboxNews.isEnabled = false
            } else {
                binding.checkbox.isEnabled = true
                binding.checkboxNews.isEnabled = true
            }
            dataIsComplete()
        }

        binding.checkbox.setOnClickListener {
            dataIsComplete()
        }

        binding.checkboxNews.setOnClickListener {
            dataIsComplete()
        }

        binding.name.doOnTextChanged { _, _, _, _ ->
            dataIsComplete()
        }
    }

    private fun validChecked(): Boolean {
        return !binding.switchNotice.isChecked
                || binding.switchNotice.isChecked && (binding.checkbox.isChecked
                || binding.checkboxNews.isChecked)
    }

    private fun showSnackbar(s: Int) {
        Snackbar.make(findViewById(android.R.id.content), s, Snackbar.LENGTH_SHORT).show()
    }

    private fun isPhoneValid(phone: CharSequence?): Boolean {
        return !phone.isNullOrEmpty() && Patterns.PHONE.matcher(phone).matches()
    }

    private fun dataIsComplete() {
        if (binding.name.length() > 0
            && !binding.textInputPhone.isErrorEnabled
            && validChecked()
        ) {
            binding.saveData.isEnabled = true
            binding.saveData.setOnClickListener {
                showSnackbar(R.string.saved)
            }
        } else binding.saveData.isEnabled = false
    }
}