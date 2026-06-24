package com.example.balinesia4

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val img = findViewById<ImageView>(R.id.imgDestination)
        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        val name = findViewById<TextView>(R.id.txtName)
        val location = findViewById<TextView>(R.id.txtLocation)
        val rating = findViewById<TextView>(R.id.txtRating)
        val description = findViewById<TextView>(R.id.txtDescription)

        val facilities = findViewById<TextView>(R.id.txtFacilities)
        val openingHours = findViewById<TextView>(R.id.txtOpeningHours)
        val ticketPrice = findViewById<TextView>(R.id.txtTicketPrice)

        val gallery1 = findViewById<ImageView>(R.id.gallery1)
        val gallery2 = findViewById<ImageView>(R.id.gallery2)
        val gallery3 = findViewById<ImageView>(R.id.gallery3)

        name.text = intent.getStringExtra("name")
        location.text = intent.getStringExtra("location")
        rating.text = intent.getStringExtra("rating")
        description.text = intent.getStringExtra("description")

        facilities.text = intent.getStringExtra("facilities")
        openingHours.text = intent.getStringExtra("hours")
        ticketPrice.text = intent.getStringExtra("price")

        img.setImageResource(
            intent.getIntExtra("image", 0)
        )

        gallery1.setImageResource(
            intent.getIntExtra("gallery1", 0)
        )

        gallery2.setImageResource(
            intent.getIntExtra("gallery2", 0)
        )

        gallery3.setImageResource(
            intent.getIntExtra("gallery3", 0)
        )

        btnBack.setOnClickListener {
            finish()
        }
    }
}