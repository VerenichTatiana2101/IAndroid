package com.example.searchpage.model

import kotlinx.coroutines.delay
import java.net.ConnectException

private const val NETWORK_ERROR = "No internet exception"

class MainRepository {
    private var count = 0

    suspend fun getData(searchData: String): String {
        delay(4_000)
        return if(++count %2 == 0){
            throw ConnectException(NETWORK_ERROR)
        } else {
            searchData
        }
    }
}