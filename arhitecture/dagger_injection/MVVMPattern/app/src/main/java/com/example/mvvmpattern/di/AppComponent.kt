package com.example.mvvmpattern.di

import com.example.mvvmpattern.presentation.MainViewModelFactory
import dagger.Component

@Component
interface AppComponent {
    fun mainViewModelFactory(): MainViewModelFactory
}