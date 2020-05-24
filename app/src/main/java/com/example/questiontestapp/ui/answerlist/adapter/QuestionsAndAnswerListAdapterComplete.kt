package com.example.questiontestapp.ui.answerlist.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView
import com.example.questiontestapp.R

import com.example.questiontestapp.data.db.entity.QuestionData
import com.example.questiontestapp.databinding.QuestionsAndAnswerItemBinding


class QuestionsAndAnswerListAdapterComplete(
    val questionsList: ArrayList<QuestionData>,
    private val context: Context
) : RecyclerView.Adapter<QuestionsAndAnswerListAdapterComplete.QuestionsViewHolder>() {

    fun updateQuestionList(newQuestionList: List<QuestionData>) {
        questionsList.clear()
        questionsList.addAll(newQuestionList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<QuestionsAndAnswerItemBinding>(
            inflater,
            R.layout.questions_and_answer_item,
            parent,
            false
        )
        return QuestionsViewHolder(
            view
        )
    }

    override fun getItemCount() = questionsList.size

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        holder.view.question = questionsList[position]

        // creating TextView programmatically
        var optionArray = questionsList[position].options_value?.split(",")
        var answerActual = questionsList[position].answer
        var chooseAnswer = questionsList[position].choose_answer

        optionArray?.forEachIndexed { index, value ->
            val tv_dynamic = TextView(context)
            tv_dynamic.id = index
            tv_dynamic.textSize = 17f
            tv_dynamic.setPadding(15, 15, 15, 15)
            tv_dynamic.text = "=> $value"
            if (chooseAnswer.toString().equals(value, true)) {
                if (answerActual.toString().equals(chooseAnswer.toString(), true)) {
                    tv_dynamic.setBackgroundColor(Color.parseColor("#2ead46"))
                } else {
                    tv_dynamic.setBackgroundColor(Color.parseColor("#ff0000"))
                }

            } else {
                tv_dynamic.setBackgroundColor(Color.parseColor("#ffffff"))
            }
            holder.view.optionLinear.addView(tv_dynamic)
        }


    }


    class QuestionsViewHolder(var view: QuestionsAndAnswerItemBinding) :
        RecyclerView.ViewHolder(view.root)



}