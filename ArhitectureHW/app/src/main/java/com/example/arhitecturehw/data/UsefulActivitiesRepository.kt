package com.example.arhitecturehw.data

import android.util.Log
import com.example.arhitecturehw.entity.UsefulActivity
import javax.inject.Inject

class UsefulActivitiesRepository @Inject constructor(
    private val usefulActivityDataSource: UsefulActivityDataSource){

    suspend fun getUsefulActivity(): UsefulActivity {
        Log.d("tag", "repository: ")
        return usefulActivityDataSource.createApiService().getRandomActivity()
    }
}
