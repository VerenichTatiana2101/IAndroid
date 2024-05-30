package com.example.wordcounter.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.wordcounter.model.Word
import kotlinx.coroutines.flow.Flow
const val LIMIT_WORDS = 5

@Dao
interface WordDao {
    @Query("SELECT * FROM words")
    fun getAll(): Flow<List<Word>>

    @Query("SELECT * FROM words LIMIT :limit")
    fun getFiveWords(limit: Int): Flow<List<Word>>

    @Insert(entity = Word::class)
    suspend fun insert(word: Word)

    @Update
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: List<Word>)

    @Query("SELECT*FROM words WHERE word LIKE :inputText")
    suspend fun searchWord(inputText: String): List<Word>
}