package com.example.memory

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.memory.databinding.ActivitySavedInFileBinding
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

private const val FILE_NAME = "testFile.txt"

/** сохранение в файл
 * по нажатию на первую кнопку сохраняет данные в файл,
 * по нажатию на вторую кнопку считываем из файла
 */
class SavedInFile : AppCompatActivity() {
    private lateinit var binding: ActivitySavedInFileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySavedInFileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val inputText = binding.editText.text
            saveText(inputText.toString())
        }

        binding.button2.setOnClickListener {
            val readedText = readText()
            binding.textView.text = readedText
        }

        // удаление файла
        deleteFile(FILE_NAME)
    }

    /**
     * метод записи в файл
     */
    private fun saveText(textForSave: String){
        var fos: FileOutputStream? = null
        try {
            /*
            * MODE_PRIVATE - перезаписывает данные
            * MODE_APPEND - дописывает в файл
            * */
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE)
            fos.write(textForSave.toByteArray())

            Toast.makeText(this, "file saved", Toast.LENGTH_SHORT).show()
        } catch (ex: IOException){
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
        } finally {
            fos?.close()
        }
    }

    /**
     * Метод чтения из файла
     */
    private fun readText(): String{
        var fin: FileInputStream? =null
        return try {
            fin = openFileInput(FILE_NAME)
            val bytes = ByteArray(fin.available())
            fin.read(bytes)
            String(bytes)
        } catch (ex: IOException){
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
            ""
        } finally {
            fin?.close()
        }
    }
}