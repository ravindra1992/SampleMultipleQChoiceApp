package com.example.questiontestapp.ui.questionList

import android.app.Application
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import com.example.questiontestapp.data.db.entity.QuestionData
import com.example.questiontestapp.data.repository.QuestionRepo
import com.example.questiontestapp.ui.BaseViewModel
import com.example.questiontestapp.ui.answerlist.QuestionAndAnswerList
import com.example.questiontestapp.util.getQuestionParseList
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class QuestionListViewModal(
    application: Application
) : BaseViewModel(application), KodeinAware {
    override val kodein by closestKodein()
    val questionRepo: QuestionRepo by instance<QuestionRepo>()

    val context = application.applicationContext
    val questionAllData = MutableLiveData<List<QuestionData>>()

    fun clickDone(view: View) {
        launch {
            var alldata = questionRepo.getAllFilledAnswer()
            if (alldata
                    .isNotEmpty() && alldata[0] == 0
            ) {
                Intent(context, QuestionAndAnswerList::class.java).also {
                    context.startActivity(it)
                }
            } else {
                Toast.makeText(context, "Please complete questions", Toast.LENGTH_LONG).show()
            }

        }
    }


    init {
        launch {
            val questionsListFinal = ArrayList<QuestionData>()
            val questionsList = getQuestionParseList(context)
            var i = 0;

            questionsList.forEach { questiondata ->
                questiondata.uid = i
                questiondata.choose_answer = ""
                questiondata.options?.forEachIndexed { index, value ->
                    questiondata.options_value +=
                        if (questiondata.options?.size?.minus(1)!! > index) "${value}," else value

                }
                questionsListFinal.add(questiondata)
                i++
            }
            if (questionsListFinal != null) {
                val values = questionRepo.getAllQuestions()
                if (values.isEmpty()) {
                    var ids = questionRepo.insertAllQuestions(questionsListFinal)
//                    ids.forEach {
////                        println("id $it")
//                    }
                } else {
                    questionRepo.deleteAllDataInTable()
                    questionRepo.insertAllQuestions(questionsListFinal)
                }
            }
            val values = questionRepo.getAllQuestions()
            questionAllData.value = values

        }


    }

}