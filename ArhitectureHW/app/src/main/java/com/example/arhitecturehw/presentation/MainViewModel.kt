package com.example.arhitecturehw.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arhitecturehw.domain.GetUsefulActivityUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val LOADING_VALUE = "Поиск"
private const val ERROR_VALUE = "Ошибка сети"


class MainViewModel @Inject constructor(
    private val useCase: GetUsefulActivityUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Success(""))
    val state = _state.asStateFlow()

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    init {
        Log.d("tag", "init: ")
    }

    fun reloadUsefulActivity() {
        viewModelScope.launch {
            _state.value = State.Loading(LOADING_VALUE) //загрузка
            Log.d("tag", "loading, reloadUsefulActivity started: ")
            try {
                val result = useCase.execute().activity
                Log.d("tag", "get activity: ")
                _state.value = State.Success(result) //успешно
                Log.d("tag", "send result: ")
            } catch (e: Exception) {
                _error.send(e.toString())
                _state.value = State.Error(ERROR_VALUE)
            }
        }
    }

    override fun onCleared() {
        Log.d("tag", "onCleared: ")
    }
}
