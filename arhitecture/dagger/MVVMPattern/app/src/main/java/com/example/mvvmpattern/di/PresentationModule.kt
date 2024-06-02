package com.example.mvvmpattern.di

import com.example.mvvmpattern.domain.GetUserInfoUseCase
import com.example.mvvmpattern.presentation.MainViewModel
import com.example.mvvmpattern.presentation.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideMainViewModel(
        getUserInfoUseCase: GetUserInfoUseCase
    ): MainViewModel{
        return MainViewModel(getUserInfoUseCase)
    }

    @Provides
    fun provideMainViewModelFactory(
        mainViewModel: MainViewModel
    ): MainViewModelFactory{
        return MainViewModelFactory(mainViewModel)
    }
}