package com.example.balinesia4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        db = DatabaseHelper(this)

        val etUsername =
            findViewById<EditText>(R.id.etUsername)

        val etPassword =
            findViewById<EditText>(R.id.etPassword)

        val btnMasuk =
            findViewById<Button>(R.id.btnMasuk)

        val txtDaftar =
            findViewById<TextView>(R.id.txtDaftar)

        btnMasuk.setOnClickListener {

            val username =
                etUsername.text.toString().trim()

            val password =
                etPassword.text.toString().trim()

            val berhasil =
                db.loginUser(
                    username,
                    password
                )

            if (berhasil) {

                Toast.makeText(
                    this,
                    "Login berhasil",
                    Toast.LENGTH_SHORT
                ).show()

                val intent =
                    Intent(
                        this,
                        DashboardActivity::class.java
                    )

                intent.putExtra(
                    "username",
                    username
                )

                startActivity(intent)

                finish()

            } else {

                Toast.makeText(
                    this,
                    "Username atau Password salah",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        txtDaftar.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    RegisterActivity::class.java
                )
            )

            finish()
        }
    }
}