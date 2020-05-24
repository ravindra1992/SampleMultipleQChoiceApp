package com.example.questiontestapp.data.db.room.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.questiontestapp.data.db.entity.User
import com.example.questiontestapp.data.db.room.DatabaseOptions.Companion.createTableUser
import com.example.questiontestapp.data.db.room.DatabaseOptions.Companion.databaseName
import com.example.questiontestapp.data.db.room.DatabaseOptions.Companion.databaseVersion
import com.example.questiontestapp.data.db.room.DatabaseOptions.Companion.emailUser
import com.example.questiontestapp.data.db.room.DatabaseOptions.Companion.passwordUser
import com.example.questiontestapp.data.db.room.DatabaseOptions.Companion.tbUser


class OpenHelper(context : Context) : SQLiteOpenHelper(context, databaseName, null, databaseVersion) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(createTableUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $tbUser")
        onCreate(db)
    }

    // CRUD
    fun registerUser(user : User) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(emailUser, user.email)
        values.put(passwordUser, user.password)
        db.insert(tbUser, null, values)
        db.close()
    }

    fun returnUser(email: String) : List<User> {
        val userList = ArrayList<User>()

        val bd = this.readableDatabase
        val query = "SELECT $emailUser FROM $tbUser WHERE $emailUser LIKE?"
        val cursor = bd.rawQuery(query, arrayOf(email))

        if (cursor.moveToFirst()) {
            do {
                val user = User(cursor.getString(0))
                userList.add(user)

            } while (cursor.moveToNext())
        }

        cursor.close()
        return userList
    }

    fun verificLogin(email : String, password : String) : Boolean {
        val data = arrayOf(email, password)

        val db = this.readableDatabase
        val query = "SELECT $emailUser, $passwordUser FROM $tbUser " +
                "WHERE $emailUser LIKE? AND $passwordUser LIKE?"
        val cursor = db.rawQuery(query, data)

        return if (cursor.count > 0) {
            cursor.close()
            true
        } else {
            cursor.close()
            false
        }
    }

}