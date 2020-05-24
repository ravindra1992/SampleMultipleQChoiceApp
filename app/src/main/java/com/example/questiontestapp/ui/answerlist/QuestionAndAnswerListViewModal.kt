package com.example.questiontestapp.ui.answerlist

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.questiontestapp.data.db.entity.QuestionData
import com.example.questiontestapp.data.repository.QuestionRepo
import com.example.questiontestapp.ui.BaseViewModel
import com.example.questiontestapp.util.getQuestionParseList
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class QuestionAndAnswerListViewModal(
    application: Application
) : BaseViewModel(application), KodeinAware {
    override val kodein by closestKodein()
    val questionRepo: QuestionRepo by instance<QuestionRepo>()

    val context = application.applicationContext
    val questionAllData = MutableLiveData<List<QuestionData>>()


    init {
        launch {
            val values = questionRepo.getAllQuestions()
            questionAllData.value = values
        }


    }

}