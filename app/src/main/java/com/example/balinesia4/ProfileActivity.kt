package com.example.balinesia4

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class ProfileActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)

        db = DatabaseHelper(this)

        val txtNama =
            findViewById<TextView>(R.id.txtNama)

        val txtEmail =
            findViewById<TextView>(R.id.txtEmail)

        val btnLogout =
            findViewById<CardView>(R.id.btnLogout)

        val btnFavorit =
            findViewById<CardView>(R.id.btnFavorit)

        val btnPengaturan =
            findViewById<CardView>(R.id.btnPengaturan)

        val btnBack =
            findViewById<ImageView>(R.id.btnBack)

        val username =
            intent.getStringExtra("username")

        if (username != null) {

            txtNama.text = username

            txtEmail.text =
                db.getEmail(username)
        }

        btnBack.setOnClickListener {
            finish()
        }

        btnLogout.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )

            finish()
        }

        btnFavorit.setOnClickListener {

            // Nanti diarahkan ke halaman Favorit
        }

        btnPengaturan.setOnClickListener {
                val intent =
                    Intent(
                        this,
                        PengaturanAkunActivity::class.java
                    )

                intent.putExtra(
                    "username",
                    username
                )

                startActivity(intent)
            }
        val btnTentang =
            findViewById<CardView>(R.id.btnTentang)
        btnTentang.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    TentangAplikasiActivity::class.java
                )
            )

        }

        }
    }
