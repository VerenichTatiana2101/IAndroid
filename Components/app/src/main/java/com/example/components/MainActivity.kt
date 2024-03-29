package com.example.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
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

                R.id.radioButtonOne -> showSnackbar("Один")
                R.id.radioButtonTwo -> showSnackbar("Два")
                R.id.radioButtonThree -> showSnackbar("Три")
                R.id.radioButtonFour -> showSnackbar("Четыре")
                NONE -> showSnackbar("Ничего")
            }
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            radioGroup.clearCheck()
        }

        //EditText
        val emailEditText = findViewById<EditText>(R.id.email)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.button_login)

        loginButton.setOnClickListener {
            val email = emailEditText.text
            val password = passwordEditText.text
            if (email.isNotBlank() && password.isNotBlank()) {
                Toast.makeText(this, "Успешно", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
            }
        }

        //TextInputLayout
        val emailTextInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        val emailEditText2 = findViewById<EditText>(R.id.email2)

        emailEditText2.doOnTextChanged { text, _, _, _ ->
            if (isEmailValid(text)) {
                emailTextInputLayout.isErrorEnabled = false
            } else {
                emailTextInputLayout.error = "Некорректный ввод"
                emailTextInputLayout.isErrorEnabled = true
            }
        }

        //применение тёмной темы
        findViewById<Button>(R.id.buttonTheme).setOnClickListener{
            if (AppCompatDelegate.getDefaultNightMode()==MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            }
        }
    }

    //radioGroup
    private fun showSnackbar(s: String) {
        Snackbar.make(findViewById(android.R.id.content), s, Snackbar.LENGTH_SHORT).show()
    }

    //TextInputLayout
    private fun isEmailValid(email: CharSequence?): Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}