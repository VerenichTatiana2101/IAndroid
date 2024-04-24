package com.quizzymania.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.quizzymania.R
import com.quizzymania.databinding.FragmentQuizBinding
import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Quiz : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd.MM.yy")

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //открытие диалогового окна с календарем при нажатии на кнопку
        binding.openDatePicker.setOnClickListener {
            //разница во времени
            val constraints = CalendarConstraints.Builder() //с01,01,1970
                .setOpenAt(calendar.timeInMillis)
                .build()

            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(constraints) //выводим предустановленную дату
                .setTitleText(R.string.choose_the_date) //указывается цель выбора даты
                .build()

            dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis = timeInMillis
                Snackbar.make(binding.openDatePicker, dateFormat.format(calendar.time), Snackbar.LENGTH_SHORT).show()
                binding.buttonStart.isEnabled = true
            }
            dateDialog.show((activity as FragmentActivity).supportFragmentManager, "DatePicker")
        }

        binding.buttonStart.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_questionFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}