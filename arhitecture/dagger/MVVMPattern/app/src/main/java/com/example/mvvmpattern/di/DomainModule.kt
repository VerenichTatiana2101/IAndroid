package com.example.mvvmpattern.di

import com.example.mvvmpattern.data.LocalStorageRepository
import com.example.mvvmpattern.data.MainRepository
import com.example.mvvmpattern.domain.GetUserInfoUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetUserInfoUseCase(
        mainRepository: MainRepository,
        localStorageRepository: LocalStorageRepository
    ): GetUserInfoUseCase{
        return GetUserInfoUseCase(mainRepository, localStorageRepository)
    }
}