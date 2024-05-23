package com.example.retrofit.ui.repository

import com.example.retrofit.ui.api.RetrofitClient
import com.example.retrofit.ui.model.UsersInfo

private const val REQUEST_ERROR = "REQUEST ERROR"

class UsersRepository() {
    suspend fun getRandomUser(): UsersInfo? {
        val response = RetrofitClient.apiService.getUser()
        if(!response.isSuccessful) throw RuntimeException(REQUEST_ERROR)

        return response.body()
    }
}