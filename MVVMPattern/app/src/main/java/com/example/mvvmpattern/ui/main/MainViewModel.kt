package com.example.mvvmpattern.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel(
    // конструктор передачи [repository]
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    init {
        Log.d(TAG, "init: ")
    }

    fun onSignClick(login: String, password: String) {
        Log.d(TAG, "onSignClick: ")
        viewModelScope.launch {
            val isLoginEmpty = login.isBlank()
            val isPasswordEmpty = password.isBlank()
            if (isLoginEmpty || isPasswordEmpty) {
                var loginError: String? = null
                if (isLoginEmpty){
                    loginError = "Login is required"
                }
                var passwordError: String? = null
                if (isPasswordEmpty){
                    passwordError = "Password is required"
                }
                _state.value = State.Error(loginError, passwordError)
                _error.send("Login or password not valid")
            } else {
                _state.value = State.Loading

                // условия для отлавливания ошибки:
                try {
                    // изменение логики на:
                    repository.getData()
                    _state.value = State.Success
                } catch (e:Exception){
                    // сообщение об ошибке
                    _error.send(e.toString())
                    // меняем состояние view на error с пустыми параметрами null
                    _state.value = State.Error(null, null)
                }
            }
        }
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared: ")
    }
}