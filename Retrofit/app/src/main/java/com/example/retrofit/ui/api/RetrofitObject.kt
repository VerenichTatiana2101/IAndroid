package com.example.retrofit.ui.api

import com.example.retrofit.ui.model.UsersInfo
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET

private const val BASE_URL = "https://randomuser.me"

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

interface ApiService {
    @GET("/api/")
    suspend fun getUser() : Response<UsersInfo>
}
