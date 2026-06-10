package com.example.balinesia4

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        username =
            intent.getStringExtra("username")

        val menuProfile =
            findViewById<LinearLayout>(R.id.menuProfile)

        menuProfile.setOnClickListener {

            Toast.makeText(
                this,
                "Profile diklik",
                Toast.LENGTH_SHORT
            ).show()

            val intent =
                Intent(
                    this,
                    ProfileActivity::class.java
                )

            intent.putExtra(
                "username",
                username
            )

            startActivity(intent)
        }
    }
}