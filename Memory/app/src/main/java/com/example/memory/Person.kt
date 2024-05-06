package com.example.memory

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String = "Tatiana",
    val age: Int = 27,
    val placeWork: String = ""
): Parcelable