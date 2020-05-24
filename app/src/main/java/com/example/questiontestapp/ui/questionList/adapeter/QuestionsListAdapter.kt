package com.example.questiontestapp.ui.questionList.adapeter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView
import com.example.questiontestapp.R

import com.example.questiontestapp.data.db.entity.QuestionData
import com.example.questiontestapp.data.db.room.AppDatabase
import com.example.questiontestapp.data.repository.QuestionRepo
import com.example.questiontestapp.databinding.QuestionsItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class QuestionsListAdapter(
    val questionsList: ArrayList<QuestionData>,
    private val context: Context
) : RecyclerView.Adapter<QuestionsListAdapter.QuestionsViewHolder>() {

    fun updateQuestionList(newQuestionList: List<QuestionData>) {
        questionsList.clear()
        questionsList.addAll(newQuestionList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<QuestionsItemBinding>(
            inflater,
            R.layout.questions_item,
            parent,
            false
        )
        return QuestionsViewHolder(view)
    }

    override fun getItemCount() = questionsList.size

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        holder.view.question = questionsList[position]

        // creating TextView programmatically
        var optionArray = questionsList[position].options_value?.split(",")

        optionArray?.forEachIndexed { index, value ->
            val tv_dynamic = TextView(context)
            tv_dynamic.id = index
            tv_dynamic.textSize = 17f
            tv_dynamic.setPadding(15, 15, 15, 15)
            tv_dynamic.text = "=> $value"
            tv_dynamic.setTag(R.id.textAnswer, value)
            tv_dynamic.setTag(R.id.textParentUid, questionsList[position].uid)
            tv_dynamic.setOnClickListener {
                onOptionClick(holder.view.optionLinear, it)
                CoroutineScope(Dispatchers.Main).launch {
                    QuestionRepo(AppDatabase.invoke(context)).updateQuestionAnswer(
                        it.getTag(R.id.textAnswer).toString()
                        , it.getTag(R.id.textParentUid) as Int
                    )
                }
            }
            // add TextView to LinearLayout
            holder.view.optionLinear.addView(tv_dynamic, index)
        }

    }

    private fun onOptionClick(linearView: LinearLayout, view: View) {
        val childCount: Int = linearView.childCount
        for (i in 0 until childCount) {
            val v: View = linearView.getChildAt(i)
            if (v.id == view.id)
                v.setBackgroundColor(Color.parseColor("#999999"))
            else
                v.setBackgroundColor(Color.parseColor("#ffffff"))
        }
    }


    class QuestionsViewHolder(var view: QuestionsItemBinding) : RecyclerView.ViewHolder(view.root)

}