package com.example.datastorage

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.datastorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        При повторном старте приложения отображается значение, которое мы записали,
         */
        var readText = repository.getText(this)
        if (readText.isNotEmpty()) {
            binding.textView.text = readText
        }

        /*
         После нажатия кнопки «Сохранить» — возьмём текстовое значение из EditText
         и передадим его в метод saveText(text: String).
         */
        binding.button1.setOnClickListener {
            val inputText = binding.editText.text.toString()
            if (inputText.isNotEmpty()) {
                repository.saveText(this, inputText)
                readText = repository.getText(this)
                binding.textView.text = readText
            } else {
                Toast.makeText(this, EMPTY_MSG, Toast.LENGTH_SHORT).show()
            }
        }

        /*
        При нажатии кнопки «Очистить» значение в репозитории меняется на null,
        а TextView отображает « » (пустая строка)
         */
        binding.button2.setOnClickListener {
            repository.clearText(this)
            binding.textView.text = NULL_TEXT
            Toast.makeText(this, CLEAR_MSG, Toast.LENGTH_SHORT).show()
        }
    }
    companion object{
        const val EMPTY_MSG = "Поле ввода пустое"
        const val CLEAR_MSG = "Текст очищен"
        const val NULL_TEXT = ""
    }
}