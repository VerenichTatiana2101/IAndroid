package com.example.mvvmpattern.domain

import com.example.mvvmpattern.data.LocalStorageRepository
import com.example.mvvmpattern.data.MainRepository
import com.example.mvvmpattern.entity.UserInfo
import javax.inject.Inject

/*
 * задача класса получить информацию о пользователе
 * получив на вход логин и пароль, авторизовав пользователя
 * сделав необходимый запрос для получения деталей о User
 */

class GetUserInfoUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val localStorageRepository: LocalStorageRepository
) {
    suspend fun getUserInfo(login: String, password: String): UserInfo {
        val userId = mainRepository.autorize(login, password)
        val userInfo = mainRepository.getUserInfo(userId)
        // добавили логику получения информации о пользователе, сохраниение в БД
        localStorageRepository.saveUserInfo(userInfo)
        return userInfo

    }
}