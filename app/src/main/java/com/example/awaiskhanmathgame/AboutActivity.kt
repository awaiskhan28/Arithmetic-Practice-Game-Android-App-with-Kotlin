package com.example.awaiskhanmathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        //go back to main menu
        val main = findViewById<Button>(R.id.btnBack)
        main.setOnClickListener {
            val init = Intent(this,MainActivity::class.java)
            startActivity(init)
            finish()

        }
    }
}