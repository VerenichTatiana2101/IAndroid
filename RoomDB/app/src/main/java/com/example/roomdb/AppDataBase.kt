package com.example.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdb.model.User

@Database(entities = [User::class], version = 1,
    exportSchema = true) //при изменениях меняется

abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}