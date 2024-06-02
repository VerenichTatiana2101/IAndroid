package com.example.mvvmpattern.di

import com.example.mvvmpattern.presentation.MainViewModelFactory
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        PresentationModule::class
    ]
)
interface AppComponent {

    fun mainViewModelFactory(): MainViewModelFactory
}