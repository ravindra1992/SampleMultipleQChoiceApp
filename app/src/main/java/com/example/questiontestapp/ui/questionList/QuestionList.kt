package com.example.questiontestapp.ui.questionList

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.questiontestapp.R
import com.example.questiontestapp.databinding.ActivityQuestionListBinding
import com.example.questiontestapp.ui.questionList.adapeter.QuestionsListAdapter
import kotlinx.android.synthetic.main.activity_question_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class QuestionList : AppCompatActivity(), KodeinAware {
    override val kodein by closestKodein()
    private val factory: QuestionsViewModelFactory by instance<QuestionsViewModelFactory>()
    private lateinit var questionsListAdapter: QuestionsListAdapter
    lateinit var questionListViewModal: QuestionListViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityQuestionListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_question_list)
        questionListViewModal =
            ViewModelProvider(this, factory).get(QuestionListViewModal::class.java)
        binding.apply {
            this.lifecycleOwner = this@QuestionList
            this.questionlistviewmodal = questionListViewModal
        }
        questionsListAdapter = QuestionsListAdapter(arrayListOf(), this)
        questionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = questionsListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        questionListViewModal.questionAllData.observe(this, Observer { questions ->
            questions?.let {
                Log.e("questionsList", questions.get(0).answer)
                questionsListAdapter.updateQuestionList(questions)

            }
        })
    }
}
