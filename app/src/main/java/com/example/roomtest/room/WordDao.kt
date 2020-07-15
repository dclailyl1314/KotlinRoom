package com.example.myapplication.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {

    @Insert
    suspend fun insert(word: Word)
    @Update
    suspend fun update(word: Word)
    @Query("DELETE FROM word")
    suspend fun deleteAll()
    @Delete
    suspend fun delete(word: Word)
    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    fun getAllWords() : List<Word>
}