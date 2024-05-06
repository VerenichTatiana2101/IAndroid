package com.example.memory

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.memory.databinding.SharedMainBinding

// название нашего хранилища
private const val PREFERENCE_NAME = "preference_name"
// создадим константу для ключа
private const val KEY_STRING_NAME = "KEY_STRING"

class SharedPreference : AppCompatActivity() {

    private lateinit var binding: SharedMainBinding
    // создадим переменную shared preference
    private lateinit var prefs: SharedPreferences
    //экземпляр класса Person
    private val person = Person("Tatiana", 34, "Mts")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SharedMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // положим соответствующее значение в переменную shared preference
        prefs = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE) //MODE_PRIVATE рекомендованный вариант

        /*
        // получим возможность записи, нужен интерфейс editor
        val editor: SharedPreferences.Editor = prefs.edit()

        // записать в shared preference какие-либо данные через editor
        editor.putString(KEY_STRING_NAME, "hello")

        /*
        * сначала вносятся все необходимые изменения
        * далее идет сохранение пачкой
        */
        // val isResultSuccess = editor.commit() //сохранение в главном потоке(не рекомендуется) boolean
        editor.apply() //предпочтительнee
        // удаление
        // editor.remove(KEY_STRING_NAME)

        // очистить хранилище
        // editor.clear()

        // считать данные из хранилища
        val myValueFromPrefs = prefs.getString(KEY_STRING_NAME, "дефолтное значение")

        // проверить наличие boolean
        val isContain = prefs.contains(KEY_STRING_NAME)
         */

        // по нажатии на кнопку будем считывать значение из хранилища
        prefs.edit().putString(KEY_STRING_NAME, "значение из хранилища").apply()
        binding.button.setOnClickListener {
            val myValueFromPrefs = prefs.getString(KEY_STRING_NAME, "дефолтное значение")
            binding.hello.text = myValueFromPrefs
        }

        val fragmentArgs = Bundle().apply {
            putParcelable("Person", person)
        }

        val fragment = MyFragment().apply {
            arguments = fragmentArgs
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putParcelable("Person", person)
    }

    // восстановление
    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        savedInstanceState?.putParcelable("person", person)
    }
}