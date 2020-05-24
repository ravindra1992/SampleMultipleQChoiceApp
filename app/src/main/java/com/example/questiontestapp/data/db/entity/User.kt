package com.example.questiontestapp.data.db.entity

class User {

    var email : String? = null
    var password : String? = null

    constructor(email : String, password : String) {
        this.email = email
        this.password = password
    }

    constructor(email : String) {
        this.email = email
    }
}