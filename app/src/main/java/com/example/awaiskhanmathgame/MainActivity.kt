package com.example.awaiskhanmathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //open about activity using about button
        val about = findViewById<Button>(R.id.btnAbout)
        about.setOnClickListener {
            val init = Intent(this,AboutActivity::class.java)
            startActivity(init)
            finish()

        }

        //open Game activity using new game button
        val game = findViewById<Button>(R.id.btnNewGame)
        game.setOnClickListener {
            val init = Intent(this,TestActivity::class.java)
            startActivity(init)
            //finish()


        }
    }
}