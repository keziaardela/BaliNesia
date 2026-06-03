package com.example.balinesia4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        db = DatabaseHelper(this)

        Toast.makeText(
            this,
            "Database aktif",
            Toast.LENGTH_SHORT
        ).show()

        val etEmail = findViewById<EditText>(R.id.etEmail)

        val etUsername = findViewById<EditText>(R.id.etUsername)

        val etPassword = findViewById<EditText>(R.id.etPassword)

        val etKonfirmasi =
            findViewById<EditText>(R.id.etKonfirmasi)

        val btnDaftar =
            findViewById<Button>(R.id.btnDaftar)

        btnDaftar.setOnClickListener {

            val email =
                etEmail.text.toString()

            val username =
                etUsername.text.toString()

            val password =
                etPassword.text.toString()

            val konfirmasi =
                etKonfirmasi.text.toString()

            if (
                email.isEmpty() ||
                username.isEmpty() ||
                password.isEmpty() ||
                konfirmasi.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Semua data harus diisi",
                    Toast.LENGTH_SHORT
                ).show()

            }

            else if (password != konfirmasi) {

                Toast.makeText(
                    this,
                    "Password tidak sama",
                    Toast.LENGTH_SHORT
                ).show()

            }

            else {

                val berhasil =
                    db.registerUser(
                        email,
                        username,
                        password
                    )

                if (berhasil) {

                    Toast.makeText(
                        this,
                        "Registrasi berhasil",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(
                        Intent(
                            this,
                            LoginActivity::class.java
                        )
                    )

                    finish()

                } else {

                    Toast.makeText(
                        this,
                        "Registrasi gagal",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val txtMasuk =
            findViewById<TextView>(R.id.txtMasuk)

        txtMasuk.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )

            finish()
        }
    }
}