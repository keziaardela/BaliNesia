package com.example.balinesia4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class RegisterLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register_login)

        val btnDaftar = findViewById<Button>(R.id.btnDaftar)

        btnDaftar.setOnClickListener {

            startActivity(
                Intent(this, RegisterActivity::class.java)
            )

        }
        val btnMasuk = findViewById<Button>(R.id.btnMasuk)

        btnMasuk.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }

    }
}