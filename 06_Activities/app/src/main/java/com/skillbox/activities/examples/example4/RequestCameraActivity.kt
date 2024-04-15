package com.skillbox.activities.examples.example4

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.skillbox.activities.BuildConfig
import com.skillbox.activities.databinding.ActivityRequestCameraBinding
import java.io.File

class RequestCameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRequestCameraBinding

    // переменная для сохранения пути до файла
    private var latestFileUri: Uri? = null

    //launcher для получения preview от камеры
    private val takePreview =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            binding.imageContainer.setImageBitmap(it)
        }

    // launcher для получения фотографии. Результат работы представлен типом Boolean:
    // true - удалось сохранить фотографию в созданный файл
    // false - не удалось сохранить фотографию
    private val takePhoto =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                // если результат работы true - значит фотография успешно сохранена в созданный файл
                latestFileUri?.let {
                    binding.imageContainer.setImageURI(it)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // создание view с помощью binding утилиты
        binding = ActivityRequestCameraBinding.inflate(layoutInflater)

        // установка обработчика нажатия на кнопку для получения preview от камеры
        binding.takePreview.setOnClickListener {
            takePreview.launch(null)
        }
        // установка обработчика нажатия на кнопку для получения photo от камеры
        binding.takePhoto.setOnClickListener {
            getFileUri().let {
                latestFileUri = it
                takePhoto.launch(it)
            }
        }
        setContentView(binding.root)
    }

    // создание временного файла
    // для работы с файловой системой необходимо описать FileProvider в файле AndroidManifest
    // конфигурация FileProvider находится в файле /res/xml/provider_paths
    private fun getFileUri(): Uri {
        val file = File.createTempFile("tmp_file", ".png").apply {
            createNewFile()
            deleteOnExit()
        }
        return FileProvider.getUriForFile(
            applicationContext,
            "${BuildConfig.APPLICATION_ID}.provider",
            file
        )
    }
}
