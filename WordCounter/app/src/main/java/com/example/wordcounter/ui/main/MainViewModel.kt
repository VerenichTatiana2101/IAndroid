package com.example.wordcounter.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordcounter.db.WordDao
import com.example.wordcounter.model.Word
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

const val ONE = 1
const val LIMIT_WORDS = 5

class MainViewModel(private val wordDao: WordDao) : ViewModel() {
    val allWords = this.wordDao.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    val limitedWords = this.wordDao.getFiveWords(LIMIT_WORDS).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun onAddBtm(inputText: String) {
        viewModelScope.launch {
            val search = wordDao.searchWord(inputText)
            if (search.isNotEmpty()) {
                search.last().let {
                    val counter = search.last().count
                    val update = it.copy(
                        count = counter + ONE
                    )
                    wordDao.update(update)
                }
            } else wordDao.insert(
                Word(
                    word = inputText,
                    count = ONE
                )
            )
        }
    }

    fun onDeleteBtn() {
        viewModelScope.launch {
            wordDao.delete(allWords.value)
        }
    }
}