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
private const val ERROR_VALUE = "Ошибка сети"

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Initialization)
    val state = _state.asStateFlow()

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    init {
        Log.d(TAG, "init: ")
    }

    fun onSignClick(searchData: String) {
        Log.d(TAG, "onSignClick called with data: $searchData")
        /*
        if (searchData.length < 3) {
            _state.value = State.Error(REQUEST_ERROR) //ошибка
        } else {*/
        viewModelScope.launch {
            _state.value = State.Loading //загрузка
            try {
                val result = repository.getData(searchData)
                _state.value = State.Success(result) //успешно
            } catch (e: Exception) {
                _error.send(e.toString())
                _state.value = State.Error(ERROR_VALUE)
            }
        }
        //}
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared: ")
    }
}