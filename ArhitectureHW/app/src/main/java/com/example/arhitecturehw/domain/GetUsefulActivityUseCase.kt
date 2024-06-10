package com.example.arhitecturehw.domain

import android.util.Log
import com.example.arhitecturehw.data.UsefulActivitiesRepository
import com.example.arhitecturehw.entity.UsefulActivity
import javax.inject.Inject

class GetUsefulActivityUseCase @Inject constructor(
    private val usefulActivitiesRepository: UsefulActivitiesRepository
) {
    suspend fun execute(): UsefulActivity {
        Log.d("tag", "execute: ")
        return usefulActivitiesRepository.getUsefulActivity()
    }
}