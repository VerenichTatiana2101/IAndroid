package com.example.wordcounter.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wordcounter.model.Word

@Database(entities = [Word::class], version = 1,
    exportSchema = true) //при изменениях меняется

abstract class AppDataBase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}