package com.example.balinesia4

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "Balinesia.db", null, 2) { // versi dinaikkan dari 1 ke 2

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE users(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "email TEXT," +
                    "username TEXT," +
                    "password TEXT)"
        )
        db.execSQL(
            "CREATE TABLE destinasi(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nama TEXT," +
                    "lokasi TEXT," +
                    "deskripsi TEXT," +
                    "gambar TEXT)"
        )
        db.execSQL(
            "CREATE TABLE trip(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_id INTEGER," +
                    "destinasi_id INTEGER," +
                    "tanggal_mulai TEXT," +
                    "tanggal_selesai TEXT," +
                    "catatan TEXT," +
                    "FOREIGN KEY(user_id) REFERENCES users(id)," +
                    "FOREIGN KEY(destinasi_id) REFERENCES destinasi(id))"
        )

        seedDestinasi(db)
    }

    private fun seedDestinasi(db: SQLiteDatabase) {
        val data = listOf(
            Triple("Pantai Kuta", "Kuta, Bali", "Pantai terkenal dengan sunset."),
            Triple("Tanah Lot", "Tabanan, Bali", "Pura di atas batu karang di tepi laut."),
            Triple("Ubud", "Gianyar, Bali", "Pusat seni dan budaya, sawah terasering.")
        )

        for ((nama, lokasi, deskripsi) in data) {
            val values = ContentValues()
            values.put("nama", nama)
            values.put("lokasi", lokasi)
            values.put("deskripsi", deskripsi)
            db.insert("destinasi", null, values)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS trip")
        db.execSQL("DROP TABLE IF EXISTS destinasi")
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun registerUser(email: String, username: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("email", email)
        values.put("username", username)
        values.put("password", password)
        val result = db.insert("users", null, values)
        return result != -1L
    }

    fun loginUser(username: String, password: String): Boolean {
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

    fun getUserEmail(username: String): String {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT email FROM users WHERE username=?", arrayOf(username))
        var email = ""
        if (cursor.moveToFirst()) {
            email = cursor.getString(0)
        }
        cursor.close()
        return email
    }

    fun updateUser(usernameLama: String, usernameBaru: String, emailBaru: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("username", usernameBaru)
        values.put("email", emailBaru)
        val result = db.update("users", values, "username=?", arrayOf(usernameLama))
        return result > 0
    }

    fun deleteUser(username: String): Boolean {
        val db = writableDatabase
        val result = db.delete("users", "username=?", arrayOf(username))
        return result > 0
    }

    fun getUserId(username: String): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT id FROM users WHERE username=?", arrayOf(username))
        var id = -1
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0)
        }
        cursor.close()
        return id
    }

    data class Destinasi(
        val id: Int,
        val nama: String,
        val lokasi: String,
        val deskripsi: String,
        val gambar: String?
    )

    fun tambahDestinasi(nama: String, lokasi: String, deskripsi: String, gambar: String? = null): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("nama", nama)
        values.put("lokasi", lokasi)
        values.put("deskripsi", deskripsi)
        values.put("gambar", gambar)
        val result = db.insert("destinasi", null, values)
        return result != -1L
    }

    fun getAllDestinasi(): List<Destinasi> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM destinasi", null)
        val list = mutableListOf<Destinasi>()

        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Destinasi(
                        id = cursor.getInt(0),
                        nama = cursor.getString(1),
                        lokasi = cursor.getString(2),
                        deskripsi = cursor.getString(3),
                        gambar = cursor.getString(4)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun getDestinasiById(id: Int): Destinasi? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM destinasi WHERE id=?", arrayOf(id.toString()))
        var hasil: Destinasi? = null
        if (cursor.moveToFirst()) {
            hasil = Destinasi(
                id = cursor.getInt(0),
                nama = cursor.getString(1),
                lokasi = cursor.getString(2),
                deskripsi = cursor.getString(3),
                gambar = cursor.getString(4)
            )
        }
        cursor.close()
        return hasil
    }
    data class Trip(
        val id: Int,
        val userId: Int,
        val destinasiId: Int,
        val tanggalMulai: String,
        val tanggalSelesai: String,
        val catatan: String?
    )

    fun tambahTrip(
        userId: Int,
        destinasiId: Int,
        tanggalMulai: String,
        tanggalSelesai: String,
        catatan: String? = null
    ): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("user_id", userId)
        values.put("destinasi_id", destinasiId)
        values.put("tanggal_mulai", tanggalMulai)
        values.put("tanggal_selesai", tanggalSelesai)
        values.put("catatan", catatan)
        val result = db.insert("trip", null, values)
        return result != -1L
    }

    fun getTripByUser(userId: Int): List<Pair<Trip, String>> {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT trip.id, trip.user_id, trip.destinasi_id, trip.tanggal_mulai, " +
                    "trip.tanggal_selesai, trip.catatan, destinasi.nama " +
                    "FROM trip " +
                    "INNER JOIN destinasi ON trip.destinasi_id = destinasi.id " +
                    "WHERE trip.user_id=?",
            arrayOf(userId.toString())
        )

        val list = mutableListOf<Pair<Trip, String>>()

        if (cursor.moveToFirst()) {
            do {
                val trip = Trip(
                    id = cursor.getInt(0),
                    userId = cursor.getInt(1),
                    destinasiId = cursor.getInt(2),
                    tanggalMulai = cursor.getString(3),
                    tanggalSelesai = cursor.getString(4),
                    catatan = cursor.getString(5)
                )
                val namaDestinasi = cursor.getString(6)
                list.add(trip to namaDestinasi)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun hapusTrip(tripId: Int): Boolean {
        val db = writableDatabase
        val result = db.delete("trip", "id=?", arrayOf(tripId.toString()))
        return result > 0
    }
}