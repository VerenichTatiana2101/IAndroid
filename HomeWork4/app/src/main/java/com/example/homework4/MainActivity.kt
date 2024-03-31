package com.example.homework4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //radioGroup
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, buttonId ->
            when (buttonId) {
                R.id.radioButtonOne -> showSnackbar("Мужской")
                R.id.radioButtonTwo -> showSnackbar("Женский")
            }
        }

        //TextInputLayout
        val phoneInputLayout = findViewById<TextInputLayout>(R.id.textInputPhone)
        val phoneEditText = findViewById<EditText>(R.id.phone)
        phoneEditText.doOnTextChanged { text, _, _, _ ->
            if (isPhoneValid(text)) phoneInputLayout.isErrorEnabled = false
            else {
                phoneInputLayout.error = "Некорректный ввод"
                phoneInputLayout.isErrorEnabled = true
            }
        }

        //checkBox
        val checkBox: MaterialCheckBox = findViewById(R.id.checkbox)
        val checkBox2: MaterialCheckBox = findViewById(R.id.checkbox2)
        // SwitchMaterial
        val switch: SwitchMaterial = findViewById(R.id.switch_material)
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                checkBox.checkedState = MaterialCheckBox.STATE_UNCHECKED
                checkBox2.checkedState = MaterialCheckBox.STATE_UNCHECKED
                checkBox.isEnabled = false
                checkBox2.isEnabled = false
            } else {
                checkBox.isEnabled = true
                checkBox2.isEnabled = true
            }
        }

        //progressBar
        val randomProgress = Random.nextInt(101)
        val pointsCount: TextView = findViewById(R.id.pointsCount)
        val progressValue: ProgressBar = findViewById(R.id.progress)
        pointsCount.text = "$randomProgress/100"
        progressValue.progress = randomProgress

        //Toast//Snackbar
        val name = findViewById<TextInputEditText>(R.id.name)
        val saveButton: Button = findViewById(R.id.save_data)
        saveButton.setOnClickListener {
            if (name.length() > 0
                && !phoneInputLayout.isErrorEnabled
                && (!switch.isChecked || switch.isChecked && (checkBox.isChecked || checkBox2.isChecked))
            ) Snackbar.make(it, R.string.saved, Snackbar.LENGTH_LONG).show()
            else Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show()
        }
    }

    //radioGroup
    private fun showSnackbar(s: String) {
        Snackbar.make(findViewById(android.R.id.content), s, Snackbar.LENGTH_SHORT).show()
    }

    //TextInputLayout
    private fun isPhoneValid(phone: CharSequence?): Boolean {
        return !phone.isNullOrEmpty() && Patterns.PHONE.matcher(phone).matches()
    }
}