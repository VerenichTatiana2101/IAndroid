package com.example.wordcounter.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words") //таблица, по умолчанию - имя класса
data class Word(

    @PrimaryKey
    @ColumnInfo(name = "word")
    val word: String,

    @ColumnInfo(name = "count")
    val count: Int
)
