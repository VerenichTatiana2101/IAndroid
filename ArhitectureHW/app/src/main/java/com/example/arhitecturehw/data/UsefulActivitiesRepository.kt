package com.example.arhitecturehw.data

import com.example.arhitecturehw.entity.UsefulActivity
import javax.inject.Inject

private const val REQUEST_ERROR = "REQUEST ERROR"

class UsefulActivitiesRepository @Inject constructor(
    private val usefulActivityDataSource: UsefulActivityDataSource){

    suspend fun getUsefulActivity(): UsefulActivity {
//        val response = usefulActivityDataSource.action.getActionFromApi()
//        if(!response.isSuccessful) throw RuntimeException(REQUEST_ERROR)
//        return response.body()
        return usefulActivityDataSource.createApiService().getRandomActivity()
    }
}
