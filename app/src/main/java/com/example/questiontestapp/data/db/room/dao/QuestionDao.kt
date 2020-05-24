package com.example.questiontestapp.data.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.questiontestapp.data.db.entity.QuestionData


@Dao
interface QuestionDao {

    @Query("Select * from questiondata")
    suspend fun getAllQuestions(): List<QuestionData>

    @Query("Select count(*) from questiondata WHERE choose_answer =''")
    suspend fun getAllFilledAnswer(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllQuestions(questionData: ArrayList<QuestionData>): List<Long>

    @Query("UPDATE questiondata SET choose_answer = :answer WHERE uid = :id")
    suspend fun update(answer: String, id: Int): Int

    @Query("DELETE FROM questiondata")
    suspend fun nukeTable()
}