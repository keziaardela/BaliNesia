package com.example.balinesia4

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "Balinesia.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            "CREATE TABLE users(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "email TEXT," +
                    "username TEXT," +
                    "password TEXT)"
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {

        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)

    }

    fun registerUser(
        email: String,
        username: String,
        password: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()

        values.put("email", email)
        values.put("username", username)
        values.put("password", password)

        val result =
            db.insert("users", null, values)

        return result != -1L
    }

    fun loginUser(
        username: String,
        password: String
    ): Boolean {

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM users WHERE username=? AND password=?",
            arrayOf(username, password)
        )

        val berhasil = cursor.count > 0

        cursor.close()

        return berhasil
    }

    fun getEmail(username: String): String {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT email FROM users WHERE username=?", arrayOf(username))
        var email = ""
        if (cursor.moveToFirst()) {
            email = cursor.getString(0)
        }
        cursor.close()
        return email
    }

    fun updateUser(oldUsername: String, newUsername: String, newEmail: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("username", newUsername)
        values.put("email", newEmail)
        val result = db.update("users", values, "username=?", arrayOf(oldUsername))
        return result > 0
    }

    fun deleteUser(username: String): Boolean {
        val db = writableDatabase
        val result = db.delete("users", "username=?", arrayOf(username))
        return result > 0
    }
}