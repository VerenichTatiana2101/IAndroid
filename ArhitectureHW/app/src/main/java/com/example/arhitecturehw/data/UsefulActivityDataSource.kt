package com.example.arhitecturehw.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

private const val BASE_URL = "https://bored-api.appbrewery.com/"

class UsefulActivityDataSource @Inject constructor() {
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(MoshiConverterFactory.create())
//        .build()
//    val action: ActionApi = retrofit.create(ActionApi::class.java)

    fun createApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    interface ApiService {
        @GET("random")
        suspend fun getRandomActivity(): UsefulActivityDto
    }
}



