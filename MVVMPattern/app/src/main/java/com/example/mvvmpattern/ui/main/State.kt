package com.example.mvvmpattern.ui.main

sealed class State {
    data object Loading: State()
    data object Success: State()
}