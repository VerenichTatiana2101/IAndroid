package com.example.mvvmpattern.di

import com.example.mvvmpattern.data.LocalStorageRepository
import com.example.mvvmpattern.data.MainRepository
import com.example.mvvmpattern.data.UserInfoDataSource
import dagger.Module
import dagger.Provides

// модуль для data слоя
@Module
class DataModule {

    @Provides
    fun provideUserInfoDataSource(): UserInfoDataSource{
        return UserInfoDataSource()
    }

    @Provides
    fun provideLocalStorageRepository(): LocalStorageRepository{
        return LocalStorageRepository()
    }

    @Provides
    fun provideMainRepository(userInfoDataSource: UserInfoDataSource): MainRepository{
        return MainRepository(userInfoDataSource)
    }
}