package com.example.mvvmpattern.data

import kotlinx.coroutines.delay

// отвечает за работу с сервисом для получения информации о пользователе
//класс компонетн data слоя
class UserInfoDataSource {

    /*
    тестовый юзер
     */
    val userInfo = UserInfoDto(
        name = "name",
        surname = "surname",
        email = "email",
        phone = "phone",
        bio = "bio"
    )
    // метод для получения пользователя по id
    suspend fun loadUserInfo(userId: Long): UserInfoDto{
        delay(5000)
        return userInfo
    }
}