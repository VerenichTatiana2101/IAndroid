package com.example.mvvmpattern.ui.main

import kotlinx.coroutines.delay
import java.net.ConnectException


// подобие [Repository] для работы с API
class MainRepository {
    // проверка на четность
    private var count = 0

    // метод получения данных
    suspend fun getData(): String {
        delay(5_000)
        return if(++count %2 == 0){
            throw ConnectException("No internet exception")
        } else {
            "Done"
        }
    }
}