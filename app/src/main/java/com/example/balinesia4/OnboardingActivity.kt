package com.example.balinesia4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OnboardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_onboarding)

        val btn = findViewById<Button>(R.id.btnLanjut)

        btn.setOnClickListener {

            startActivity(
                Intent(this, RegisterLoginActivity::class.java)
            )

        }
    }
}