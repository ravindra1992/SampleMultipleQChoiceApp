package com.example.questiontestapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

const val CURRENT_ID = 0

@Entity
data class QuestionData(
    var question: String? = null,
    var answer: String? = null,
    var choose_answer: String? = null,
    var options_value: String? = "",
    @Ignore var options: Array<String>? = null
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

}