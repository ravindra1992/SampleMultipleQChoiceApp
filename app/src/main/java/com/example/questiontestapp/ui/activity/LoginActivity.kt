package com.example.questiontestapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.questiontestapp.R
import com.example.questiontestapp.data.db.room.dao.OpenHelper
import com.example.questiontestapp.ui.activity.RegisterActivity
import com.example.questiontestapp.ui.questionList.QuestionList
import com.example.questiontestapp.util.SharedPreferences

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }



    fun login(view: View) {
        val preferences = SharedPreferences.getInstance(this)
        val database = OpenHelper(this)

        val email = edtEmailLogin.text.trim().toString()
        val password = edtPasswordLogin.text.trim().toString()

        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.toastRegister), Toast.LENGTH_SHORT).show()

        } else {
            var result = database.verificLogin(email, password)

            if(result) {
                preferences.addString(getString(R.string.email), email)
                preferences.addBoolean(getString(R.string.logged), true)
                Toast.makeText(this, getString(R.string.sucessLogin), Toast.LENGTH_SHORT).show()

                val intent = Intent(this, QuestionList::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, getString(R.string.failedLogin), Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun register(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}
