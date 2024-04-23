package com.localization

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.localization.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd-MM-yy hh:mm")

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val array: Array<String> = resources.getStringArray(R.array.planets)
        val plan = array.filter { it.startsWith("М") }
        val intArray: IntArray = resources.getIntArray(R.array.years)
        val count = resources.getQuantityString(R.plurals.count_new_messages, 5, 5)
        binding.text.text = count

        binding.openDatePicker.setOnClickListener {
//            MaterialDatePicker.Builder.datePicker()
//                //указывается цель выбора даты
//                .setTitleText(R.string.choose_the_date)
//                .build()
//                .show(supportFragmentManager, "DatePicker")
            //разница во времени
            val constraints = CalendarConstraints.Builder() //с01,01,1970
                .setOpenAt(calendar.timeInMillis)
                .build()

            val dateDialog = MaterialDatePicker.Builder.datePicker()
                //выводим предустановленную дату
                .setCalendarConstraints(constraints)

                //указывается цель выбора даты
                .setTitleText(R.string.choose_the_date) //не сохраняется??
                .build()

            dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis = timeInMillis
//                val day = calendar.get(Calendar.DAY_OF_MONTH)
//                val mouth = calendar.get(Calendar.MONTH) + 1
//                val year = calendar.get(Calendar.YEAR)
                //val textDate = "$day /$mouth /$year"
                Snackbar.make(binding.openDatePicker, dateFormat.format(calendar.time), Snackbar.LENGTH_SHORT).show()
            }
            dateDialog.show(supportFragmentManager, "DatePicker")
        }

        binding.openTimePicker.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText(R.string.choose_the_time)
                //сохранияем выбранное время, при повторном открытии виден предыдущий выбор
                .setHour(calendar.get(Calendar.HOUR))
                .setMinute(calendar.get(Calendar.MINUTE))

                //apply отловит введённое пользователем время
                .build().apply {
                    addOnPositiveButtonClickListener {
                        calendar.set(Calendar.HOUR, this.hour)
                        calendar.set(Calendar.MINUTE, this.minute)

//                        val time = "${calendar.get(Calendar.HOUR)} : ${calendar.get(Calendar.MINUTE)}"
//                        Snackbar.make(binding.openTimePicker, time, Snackbar.LENGTH_SHORT).show()
                          Snackbar.make(binding.openTimePicker, dateFormat.format(calendar.time), Snackbar.LENGTH_SHORT).show()

                    }
                }
            timePicker.show(supportFragmentManager, "TimePicker")
        }
    }
}


