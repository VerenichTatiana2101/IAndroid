package com.example.roomdb.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.UserDao
import com.example.roomdb.model.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel() {
    val allUsers = this.userDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun onAddBtm() {
        val size = allUsers.value.size
        viewModelScope.launch {
            userDao.insert(
                User(
                    id = size,
                    firstName = "Name $size",
                    lastName = "LastName $size",
                    age = size
                )
            )
        }
    }

    fun onUpdateBtn() {
        viewModelScope.launch {
            allUsers.value.lastOrNull()?.let {
                val user = it.copy(
                    lastName = "Petrov"
                )
                userDao.update(user)
            }
        }
    }

    fun onDeleteBtn() {
        viewModelScope.launch {
            allUsers.value.lastOrNull()?.let {
                userDao.delete(it)
            }
        }
    }
}