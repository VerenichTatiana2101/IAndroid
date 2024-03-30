package com.example.homework4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputLayout
import kotlin.random.Random

private const val NONE = -1;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //radioGroup
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, buttonId ->
            when (buttonId) {
                R.id.radioButtonOne -> showSnackbar("Мужской")
                R.id.radioButtonTwo -> showSnackbar("Женский")
                NONE -> showSnackbar("Ничего")
            }
        }

        //TextInputLayout
        val phoneInputLayout = findViewById<TextInputLayout>(R.id.textInputPhone)
        val phoneEditText = findViewById<EditText>(R.id.phone)
        phoneEditText.doOnTextChanged { text, _, _, _ ->
            if (isPhoneValid(text)) {
                phoneInputLayout.isErrorEnabled = false
            } else {
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