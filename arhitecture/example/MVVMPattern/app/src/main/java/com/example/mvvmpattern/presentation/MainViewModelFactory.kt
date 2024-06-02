package com.example.mvvmpattern.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmpattern.data.LocalStorageRepository
import com.example.mvvmpattern.data.MainRepository
import com.example.mvvmpattern.data.UserInfoDataSource
import com.example.mvvmpattern.domain.GetUserInfoUseCase

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            val userInfoDataSource = UserInfoDataSource()
            val mainRepository = MainRepository(userInfoDataSource)
            val localStorageRepository = LocalStorageRepository()
            val useCase = GetUserInfoUseCase(mainRepository, localStorageRepository)
            return MainViewModel(useCase) as T
        }
        throw IllegalArgumentException("unknown class name")
    }
}