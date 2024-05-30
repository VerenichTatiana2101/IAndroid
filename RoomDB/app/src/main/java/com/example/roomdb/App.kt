package com.example.roomdb

import android.app.Application
import androidx.room.Room

class App: Application() {
    lateinit var db: AppDataBase
        //private set

    // создание таблицы в памяти
    override fun onCreate() {
        super.onCreate()

//        INSTANCE = this
//        db = Room
//            .inMemoryDatabaseBuilder(
//                this,
//                AppDataBase::class.java,
//            )
//            .fallbackToDestructiveMigration() // если нет иинструкций пересоздаёт БД
//            .build()

        db = Room.inMemoryDatabaseBuilder(
            this,
            AppDataBase::class.java
        ).build()
    }

    companion object{
        lateinit var INSTANCE: App
            private set
    }
}