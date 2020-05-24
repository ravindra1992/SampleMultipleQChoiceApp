package com.example.questiontestapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.questiontestapp.R
import com.example.questiontestapp.data.db.entity.User
import com.example.questiontestapp.data.db.room.dao.OpenHelper
import com.example.questiontestapp.util.isEmailValid
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun doRegister(view: View) {
        val database = OpenHelper(this)

        val email = edtEmailRegister.text.trim().toString()
        val password = edtPasswordRegister.text.trim().toString()

        if(email.isNullOrEmpty() || !isEmailValid(email) ) {
            Toast.makeText(this, getString(R.string.notValidEmail), Toast.LENGTH_SHORT).show()
        } else if(password.isNullOrEmpty() || password.length<5)
        {
            Toast.makeText(this, getString(R.string.toastRegister), Toast.LENGTH_SHORT).show()
        }
        else {
            database.registerUser(User(email, password))
            Toast.makeText(this, getString(R.string.sucessRegister), Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
