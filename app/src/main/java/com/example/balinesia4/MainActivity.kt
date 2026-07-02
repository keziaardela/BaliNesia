package com.example.balinesia4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        username = intent.getStringExtra("username")

        val cardKelingking = findViewById<CardView>(R.id.cardKelingking)
        cardKelingking.setOnClickListener {

            val intent = Intent(this, DetailActivity::class.java)

            intent.putExtra("name", "Pantai Kelingking")
            intent.putExtra("location", "Nusa Penida, Bali")
            intent.putExtra("rating", "4.8")
            intent.putExtra("image", R.drawable.pantai_kelingking)

            intent.putExtra(
                "description",
                "Pantai Kelingking merupakan salah satu destinasi wisata paling ikonik di Bali yang terkenal dengan tebing berbentuk menyerupai T-Rex. Dari atas tebing, pengunjung dapat menikmati pemandangan laut biru yang luas dan panorama alam yang menakjubkan."
            )

            intent.putExtra(
                "facilities",
                "📸 Spot Foto • 🅿️ Parkir • 🍽️ Warung • 🚻 Toilet"
            )

            intent.putExtra(
                "hours",
                "06:00 - 18:00 WITA"
            )

            intent.putExtra(
                "price",
                "Dewasa : Rp10.000\nAnak-anak : Rp5.000"
            )

            intent.putExtra("gallery1", R.drawable.pantai_kelingking)
            intent.putExtra("gallery2", R.drawable.pantai_kelingking)
            intent.putExtra("gallery3", R.drawable.pantai_kelingking)

            startActivity(intent)
        }

        val cardUlunDanu = findViewById<CardView>(R.id.cardUlunDanu)
        cardUlunDanu.setOnClickListener {

            val intent = Intent(this, DetailActivity::class.java)

            intent.putExtra("name", "Pura Ulun Danu Beratan")
            intent.putExtra("location", "Bedugul, Tabanan, Bali")
            intent.putExtra("rating", "4.6")
            intent.putExtra("image", R.drawable.pura_ulun_danu_bratan)

            intent.putExtra(
                "description",
                "Pura Ulun Danu Beratan adalah pura yang terletak di tepi Danau Beratan dan menjadi salah satu landmark terkenal di Bali. Suasana sejuk pegunungan serta pemandangan danau yang indah menjadikan tempat ini cocok untuk wisata keluarga maupun wisata budaya."
            )

            intent.putExtra(
                "facilities",
                "📸 Spot Foto • 🅿️ Parkir • 🍽️ Restoran • 🚻 Toilet"
            )

            intent.putExtra(
                "hours",
                "07:00 - 19:00 WITA"
            )

            intent.putExtra(
                "price",
                "Domestik : Rp50.000\nMancanegara : Rp75.000"
            )

            intent.putExtra("gallery1", R.drawable.pura_ulun_danu_bratan)
            intent.putExtra("gallery2", R.drawable.pura_ulun_danu_bratan)
            intent.putExtra("gallery3", R.drawable.pura_ulun_danu_bratan)

            startActivity(intent)
        }

        val cardTegalalang = findViewById<CardView>(R.id.cardTegalalang)
        cardTegalalang.setOnClickListener {

            val intent = Intent(this, DetailActivity::class.java)

            intent.putExtra("name", "Tegalalang Rice Terrace")
            intent.putExtra("location", "Ubud, Gianyar, Bali")
            intent.putExtra("rating", "4.7")
            intent.putExtra("image", R.drawable.tegalalang)

            intent.putExtra(
                "description",
                "Tegalalang terkenal dengan hamparan sawah berundak yang hijau dan pemandangan alam yang memukau. Destinasi ini menawarkan pengalaman menikmati keindahan pedesaan Bali serta berbagai spot foto yang menarik."
            )

            intent.putExtra(
                "facilities",
                "📸 Spot Foto • ☕ Cafe • 🅿️ Parkir • 🚻 Toilet"
            )

            intent.putExtra(
                "hours",
                "08:00 - 18:00 WITA"
            )

            intent.putExtra(
                "price",
                "Masuk Gratis\nDonasi Sukarela"
            )

            intent.putExtra("gallery1", R.drawable.tegalalang)
            intent.putExtra("gallery2", R.drawable.tegalalang)
            intent.putExtra("gallery3", R.drawable.tegalalang)

            startActivity(intent)
        }

        val fabGoBot = findViewById<FloatingActionButton>(R.id.fabGoBot)

        fabGoBot.setOnClickListener {

            startActivity(
                Intent(this, GoBotActivity::class.java)
            )
        }

        val bottomNav =
            findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.nav_home -> true

                R.id.nav_profile -> {

                    val intent =
                        Intent(this, ProfileActivity::class.java)

                    intent.putExtra(
                        "username",
                        username
                    )

                    startActivity(intent)

                    true
                }

                R.id.nav_destinasi -> {
                    startActivity(
                        Intent(this, SearchActivity::class.java)
                    )
                    true
                }

                else -> true
            }
        }
    }


}