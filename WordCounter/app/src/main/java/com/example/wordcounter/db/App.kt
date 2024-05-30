package com.example.wordcounter.db

import android.app.Application
import androidx.room.Room


class App: Application() {
    lateinit var db: AppDataBase

    // создание таблицы в памяти
    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "db"
        ).build()
    }
}