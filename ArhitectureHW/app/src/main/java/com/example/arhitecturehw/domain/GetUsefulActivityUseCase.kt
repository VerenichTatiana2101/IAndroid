package com.example.arhitecturehw.domain

import com.example.arhitecturehw.data.UsefulActivitiesRepository
import com.example.arhitecturehw.entity.UsefulActivity
import javax.inject.Inject

class GetUsefulActivityUseCase @Inject constructor(
    private val usefulActivitiesRepository: UsefulActivitiesRepository
) {
    suspend fun execute(): UsefulActivity {
        return usefulActivitiesRepository.getUsefulActivity()
    }
}