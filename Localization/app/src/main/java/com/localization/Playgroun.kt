package com.localization

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Calendar.DAY_OF_MONTH
import java.util.Calendar.MONTH
import java.util.Calendar.YEAR
import java.util.TimeZone

@SuppressLint("SimpleDateFormat")
fun main(){
    val calendar = Calendar.getInstance()
    println(calendar.timeInMillis)

    // выставить календарю другой год
    calendar.set(Calendar.YEAR, 2006)

    val day = calendar.get(DAY_OF_MONTH)
    val mouth = calendar.get(MONTH) + 1
    val year = calendar.get(YEAR)

    println("$day /$mouth /$year")

    //форматы дата/время
    val dateFormat = SimpleDateFormat("dd-MM-yy hh:mm")
    val date = calendar.time

    val formatter = dateFormat.format(date)
    println(formatter)

    //время в зависимости от часового пояса
    dateFormat.timeZone = TimeZone.getTimeZone("Europe/Madrid")
    var formatDateMadrid = dateFormat.format(calendar.time)
    println("Дата в Мадриде: $formatDateMadrid")

    //локаль для гринвича
    dateFormat.timeZone = TimeZone.getTimeZone("GTM")
    formatDateMadrid = dateFormat.format(calendar.time)
    println("Дата по Гринвичу: $formatDateMadrid")

}