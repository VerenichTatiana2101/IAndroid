package com.example.retrofit.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.ui.model.State
import com.example.retrofit.ui.repository.UsersRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"
private const val ERROR_VALUE = "Ошибка сети"
private const val SEARCH = "Поиск..."

class MainViewModel(
    private val repository: UsersRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading(SEARCH))
    val state = _state.asStateFlow()

    private var searchJob: Job? = null

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    init {
        Log.d(TAG, "init: ")
    }

    fun onSignClick() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            try {
                _state.value = State.Loading(SEARCH)
                val result = repository.getRandomUser()?.results?.get(0)
                val photo = result?.picture?.large
                _state.value = State.Success(result.toString(), photo) //успешно
            } catch (e: Exception) {
                _error.send(e.toString())
                _state.value = State.Error(ERROR_VALUE)
            }
        }
    }
}
