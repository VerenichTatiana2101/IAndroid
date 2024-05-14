package com.example.searchpage.model

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"
private const val ERROR_VALUE = "Ошибка сети"
private const val LOADING_VALUE = "Поиск"
private const val START_VALUE = "Здесь будет отображаться результат запроса"

@OptIn(FlowPreview::class)
class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Initialization)
    val state = _state.asStateFlow()

    val searchData = MutableStateFlow("")
    private var searchJob: Job? = null

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    init {
        Log.d(TAG, "init: ")
        searchData.debounce(300)
            .onEach { newData -> onSignClick(newData) }
            .launchIn(viewModelScope)
    }

    private fun onSignClick(newData: String) {
        searchJob?.cancel()
        if (newData.length < 3) {
            _state.value = State.Error(START_VALUE)
        } else {
            searchJob = viewModelScope.launch {
                _state.value = State.Loading(LOADING_VALUE) //загрузка
                try {
                    val result = repository.getData(newData)
                    _state.value = State.Success(result) //успешно
                } catch (e: Exception) {
                    _error.send(e.toString())
                    _state.value = State.Error(ERROR_VALUE)
                }
            }
        }
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared: ")
    }
}