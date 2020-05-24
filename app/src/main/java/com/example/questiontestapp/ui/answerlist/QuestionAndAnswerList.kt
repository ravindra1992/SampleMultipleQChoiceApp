package com.example.questiontestapp.ui.answerlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.questiontestapp.R
import com.example.questiontestapp.databinding.ActivityQuestionAndAnswerListBinding
import com.example.questiontestapp.databinding.ActivityQuestionListBinding
import com.example.questiontestapp.ui.answerlist.adapter.QuestionsAndAnswerListAdapterComplete
import com.example.questiontestapp.ui.questionList.adapeter.QuestionsListAdapter
import kotlinx.android.synthetic.main.activity_question_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class QuestionAndAnswerList : AppCompatActivity(), KodeinAware {
    override val kodein by closestKodein()
    private val factory: QuestionsAndAnswerViewModelFactory by instance<QuestionsAndAnswerViewModelFactory>()
    private lateinit var questionsAndAnswerListAdapterComplete: QuestionsAndAnswerListAdapterComplete
    lateinit var questionAndAnswerListViewModal: QuestionAndAnswerListViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityQuestionAndAnswerListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_question_and_answer_list)
        questionAndAnswerListViewModal =
            ViewModelProvider(this, factory).get(QuestionAndAnswerListViewModal::class.java)
        binding.apply {
            this.lifecycleOwner = this@QuestionAndAnswerList
            this.answerlistviewmodal = questionAndAnswerListViewModal
        }
        questionsAndAnswerListAdapterComplete = QuestionsAndAnswerListAdapterComplete(arrayListOf(), this)
        questionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = questionsAndAnswerListAdapterComplete
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        questionAndAnswerListViewModal.questionAllData.observe(this, Observer { questions ->
            questions?.let {
                questionsAndAnswerListAdapterComplete.updateQuestionList(questions)

            }
        })
    }

}
