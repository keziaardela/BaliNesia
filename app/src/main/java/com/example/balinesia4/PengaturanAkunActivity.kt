package com.example.balinesia4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class PengaturanAkunActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pengaturan_akun)

        db = DatabaseHelper(this)

        val etNama =
            findViewById<EditText>(R.id.etNama)

        val etEmail =
            findViewById<EditText>(R.id.etEmail)

        val btnSimpan =
            findViewById<Button>(R.id.btnSimpan)

        val btnHapus =
            findViewById<Button>(R.id.btnHapus)

        val btnBack =
            findViewById<ImageView>(R.id.btnBack)

        val usernameLama =
            intent.getStringExtra("username") ?: ""

        etNama.setText(usernameLama)

        etEmail.setText(
            db.getEmail(usernameLama)
        )

        // Tombol kembali
        btnBack.setOnClickListener {
            finish()
        }

        // Simpan perubahan
        btnSimpan.setOnClickListener {

            val usernameBaru =
                etNama.text.toString().trim()

            val emailBaru =
                etEmail.text.toString().trim()

            val berhasil =
                db.updateUser(
                    usernameLama,
                    usernameBaru,
                    emailBaru
                )

            if (berhasil) {

                Toast.makeText(
                    this,
                    "Data berhasil diperbarui",
                    Toast.LENGTH_SHORT
                ).show()

                val intent =
                    Intent(
                        this,
                        ProfileActivity::class.java
                    )

                intent.putExtra(
                    "username",
                    usernameBaru
                )

                intent.putExtra(
                    "email",
                    emailBaru
                )

                startActivity(intent)

                finish()

            } else {

                Toast.makeText(
                    this,
                    "Gagal memperbarui data",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Hapus akun
        btnHapus.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("Hapus Akun")
                .setMessage(
                    "Apakah Anda yakin ingin menghapus akun ini?"
                )
                .setPositiveButton("Ya") { _, _ ->

                    val berhasil =
                        db.deleteUser(
                            usernameLama
                        )

                    if (berhasil) {

                        Toast.makeText(
                            this,
                            "Akun berhasil dihapus",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(
                            Intent(
                                this,
                                LoginActivity::class.java
                            )
                        )

                        finishAffinity()

                    } else {

                        Toast.makeText(
                            this,
                            "Gagal menghapus akun",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }
}