package com.example.questiontestapp.data.db.room

class DatabaseOptions {

    companion object {
        const val databaseName = "user.db"
        const val databaseVersion = 1

        /* TABLES */
        const val tbUser = "tbUser"
        private const val idUser = "idUser"
        const val emailUser = "emailUser"
        const val passwordUser = "passwordUser"

        /* CREATE TABLE QUERY */
        const val createTableUser =
            ("CREATE TABLE $tbUser (" +
                "$idUser INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", $emailUser TEXT" +
                ", $passwordUser TEXT" +
            ")")
    }
}