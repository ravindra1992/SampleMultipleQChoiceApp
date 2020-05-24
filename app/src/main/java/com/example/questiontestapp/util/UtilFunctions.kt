package com.example.questiontestapp.util

import android.content.Context
import com.example.questiontestapp.data.db.entity.QuestionData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

fun getQuestionParseList(context: Context): ArrayList<QuestionData> {
    val jsonString =
        context.applicationContext.assets.open("json/questionsjson.json").bufferedReader()
            .use {
                it.readText()
            }
//    println(jsonString)
    val type: Type = object : TypeToken<ArrayList<QuestionData>>() {}.type
    return Gson().fromJson(jsonString, type)
}

fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
