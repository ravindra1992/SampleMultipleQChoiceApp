package com.example.questiontestapp.data.repository

import com.example.questiontestapp.data.db.entity.QuestionData
import com.example.questiontestapp.data.db.room.AppDatabase

class QuestionRepo(
    private val appDatabase: AppDatabase
) {
    suspend fun getAllQuestions(): List<QuestionData> {
        return appDatabase.getQuestionsDao().getAllQuestions()
    }

    suspend fun insertAllQuestions(questionData: ArrayList<QuestionData>): List<Long> {
        return appDatabase.getQuestionsDao().insertAllQuestions(questionData)
    }

    suspend fun updateQuestionAnswer(answer: String, uid: Int) {
        appDatabase.getQuestionsDao().update(answer, uid)
    }
    suspend fun getAllFilledAnswer() : List<Int> {
        return appDatabase.getQuestionsDao().getAllFilledAnswer()
    }
    suspend fun deleteAllDataInTable(){
        appDatabase.getQuestionsDao().nukeTable()
    }
}