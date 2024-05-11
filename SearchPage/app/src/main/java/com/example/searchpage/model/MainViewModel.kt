package com.example.searchpage.model

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"
private const val REQUEST_ERROR = "Ошибка запроса"
private const val REQUEST_EMPTY = "Запрос не найден"

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()
    private var count = 0

    init {
        Log.d(TAG, "init: ")

    }

    fun onSignClick(searchData: String) {
        Log.d(TAG, "onSignClick called with data: $searchData")
        viewModelScope.launch {
            /* обработка отсутствия и ошибочного ввода запроса*/
            if (searchData.length < 3) {
                _state.value = State.Error(REQUEST_ERROR) //ошибка
                _error.send(REQUEST_ERROR)
            } else {
                /*
                * при верном вводе имитация
                * часть вывод результата
                * часть - ошибка сети
                * часть - результат не найден
                */
                _state.value = State.Loading //загрузка

                try {
                    repository.getData()
                    if (count++ % 2 == 0) _state.value = State.Success //успешно
                    else _state.value = State.Error(REQUEST_EMPTY) //ошибка

                } catch (e: Exception) {
                    _error.send(e.toString())
                    _state.value = State.Error(null)
                }
            }
        }
    }


    override fun onCleared() {
        Log.d(TAG, "onCleared: ")
    }
}