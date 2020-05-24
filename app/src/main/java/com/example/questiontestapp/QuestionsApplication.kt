package com.example.questiontestapp

import android.app.Application
import com.example.questiontestapp.data.db.room.AppDatabase
import com.example.questiontestapp.data.repository.QuestionRepo
import com.example.questiontestapp.ui.answerlist.QuestionsAndAnswerViewModelFactory
import com.example.questiontestapp.ui.questionList.QuestionsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class QuestionsApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@QuestionsApplication))

        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton {QuestionRepo(instance())}
        bind() from provider { QuestionsViewModelFactory(instance()) }
        bind() from provider { QuestionsAndAnswerViewModelFactory(instance()) }

    }
}