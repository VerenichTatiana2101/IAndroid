package com.example.arhitecturehw.data

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val BASE_URL = "https://bored-api.appbrewery.com/"

class UsefulActivityDataSource @Inject constructor() {
    fun createApiService(): ApiService {
        Log.d("tag", "createApi: ")

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                val request = chain.request()
                val startNs = System.nanoTime()
                try {
                    val response = chain.proceed(request)
                    val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
                    Log.d("tag", "Request to ${request.url} took $tookMs ms")
                    response
                } catch (e: Exception) {
                    Log.e("tag", "Failed to make request to ${request.url}", e)
                    throw e
                }
            }
            .build()

        Log.d("tag", "try return")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    interface ApiService {
        @GET("random")
        suspend fun getRandomActivity(): UsefulActivityDto
    }
}



