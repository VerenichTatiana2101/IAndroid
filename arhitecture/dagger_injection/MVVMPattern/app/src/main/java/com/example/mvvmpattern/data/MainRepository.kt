package com.example.mvvmpattern.data

import com.example.mvvmpattern.entity.UserInfo
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val userInfoDataSource: UserInfoDataSource
) {

    // тут будет обращение к сети с помощью okhttp, retrofit
    fun autorize(login: String, password: String): Long {
        return 1L
    }

    suspend fun getUserInfo(userId: Long): UserInfo{
        return userInfoDataSource.loadUserInfo(userId)
    }
}