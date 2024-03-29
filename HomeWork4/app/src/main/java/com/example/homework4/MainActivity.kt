package com.example.homework4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

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