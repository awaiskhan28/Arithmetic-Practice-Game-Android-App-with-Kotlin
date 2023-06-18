package com.example.awaiskhanmathgame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class TestActivity : AppCompatActivity() {

    var count = 0 //total correct question
    var wrongCount = 0 //total wrong question
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val Greater = findViewById<Button>(R.id.btnGreater)
        val equals = findViewById<Button>(R.id.btnEguals)
        val less = findViewById<Button>(R.id.btnLess)
        val mainBtn = findViewById<Button>(R.id.btnMain)

        val start = findViewById<Button>(R.id.btnStart)
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvTimer = findViewById<TextView>(R.id.tvTimer)
        val tvDone = findViewById<TextView>(R.id.tvDone)
        val tv1 = findViewById<TextView>(R.id.tvExpression1)
        val tv2 = findViewById<TextView>(R.id.tvExpression2)
        val tvHint = findViewById<TextView>(R.id.tvHint)

        //first set the comparision buttons invisible
        Greater.visibility = View.INVISIBLE
        equals.visibility = View.INVISIBLE
        less.visibility = View.INVISIBLE
        mainBtn.visibility = View.INVISIBLE

        //click start button to start game and timer
        start .setOnClickListener{
            Game()//start game
            //hide the start button
            start.visibility = View.INVISIBLE
            tvHint.visibility = View.INVISIBLE

            //set he comparison buttons to visible
            Greater.visibility = View.VISIBLE
            equals.visibility = View.VISIBLE
            less.visibility = View.VISIBLE
            mainBtn.visibility = View.VISIBLE

            //timer
            object : CountDownTimer(50000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    tvTimer.text = "seconds remaining: " + millisUntilFinished / 1000

                    //update timer
                    if (count < 5){
                        //tvTimer.text = "seconds remaining: " + millisUntilFinished / 1000
                    }
                    else {
                        var update = millisUntilFinished+10000
                        //tvTimer.text = "seconds remaining: " + update / 1000
                    }

                    //alert time is below 10 seconds
                    if (millisUntilFinished < 10000){
                        tvTimer.setTextColor(Color.parseColor("#FF0000"))
                    }


                }

                override fun onFinish() {
                    //on finish hide the buttons and expressions
                    Greater.visibility = View.INVISIBLE
                    equals.visibility = View.INVISIBLE
                    less.visibility = View.INVISIBLE
                    tv1.visibility = View.INVISIBLE
                    tv2.visibility = View.INVISIBLE
                    //display correct answers and wrong answers
                    tvTimer.text = "Times up!!"
                    //tvDone.setTextColor(Color.parseColor("#FFFFFF"))
                    tvDone.text = "Correct score: $count   Wrong score: $wrongCount"
                    val total = count + wrongCount //total questions generated
                    tvScore.text = "$count/$total"
                    tvDone.setTextColor(Color.parseColor("#FFFFFF"))
                    //Activate the main menu button
                    Main()

                }
            }.start()

        }

    }

    //function to return to main menu after game has finished
    private fun Main(){
        findViewById<Button>(R.id.btnMain).setOnClickListener {
            val init = Intent(this,MainActivity::class.java)
            startActivity(init)
            finish()
        }
    }


    //Game Function
    private fun Game(){
        //define buttons and textviews
        val tv1 = findViewById<TextView>(R.id.tvExpression1)
        val tv2 = findViewById<TextView>(R.id.tvExpression2)
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvDone = findViewById<TextView>(R.id.tvDone)
        val Greater = findViewById<Button>(R.id.btnGreater)
        val equals = findViewById<Button>(R.id.btnEguals)
        val less = findViewById<Button>(R.id.btnLess)
        //generate numbers
        val num1 = Random.nextInt(1,10)
        val num2 = Random.nextInt(1,5)
        val num3 = Random.nextInt(1,7)
        val num4 = Random.nextInt(1,9)

        //generate operator and expression
        when (Random.nextInt(1,4)) {
            1 -> {
                val result1 = (num1 + num2) * num4
                tv1.text = "($num1 + $num2) * $num4"
                val result2 = (num3 + num2)
                tv2.text = "($num3 + $num2)"
                //count++

                //greater than
                Greater.setOnClickListener {
                    if (result1 > result2){

                        tvDone.text = "CORRECT" //print correct if correct
                        tvDone.setTextColor(Color.parseColor("#00FF00"))
                        count++ //update the correct score count
                        tvScore.text = "$count"
                        Game() //generate other pair of expressions
                    }
                    else {

                        tvDone.text = "WRONG" //print wrong in a red color if score is wrong
                        tvDone.setTextColor(Color.parseColor("#FF0000"))
                        wrongCount++ //update the wrong score count
                        Game() //generate other pair of expressions
                    }
                }
                //Equals
                equals.setOnClickListener {
                    if (result1 == result2 ){
                        tvDone.text = "CORRECT"
                        tvDone.setTextColor(Color.parseColor("#00FF00"))
                        count++
                        tvScore.text = "$count"
                        Game()
                    }
                    else {
                        tvDone.text = "WRONG"
                        tvDone.setTextColor(Color.parseColor("#FF0000"))
                        wrongCount++
                        //tvScore.text = "$count / 10"
                        Game()
                    }
                }

                //less than
                less.setOnClickListener {
                    if (result1.toInt() < result2.toInt() ){
                        tvDone.text = "CORRECT"
                        tvDone.setTextColor(Color.parseColor("#00FF00"))
                        count++
                        tvScore.text = "$count"
                        Game()
                    }
                    else {
                        tvDone.text = "WRONG"
                        tvDone.setTextColor(Color.parseColor("#FF0000"))
                        wrongCount++
          //              tvScore.text = "$count / 10"
                        Game()
                    }
                }
            }
            2 -> {
                val result1 = (num3 + num4) + (num3 * num2)
                tv1.text = "($num3 + $num4) + ($num3 * $num2)"
                val result2 = (num1 * num4) - num2
                tv2.text = "($num1 * $num4) - $num2"

                //greater than
                findViewById<Button>(R.id.btnGreater).setOnClickListener {
                    if (result1.toInt() > result2.toInt()){
                        tvDone.text = "CORRECT"
                        tvDone.setTextColor(Color.parseColor("#00FF00"))
                        count++
                        tvScore.text = "$count"
                        Game()
                    }
                    else {
                        tvDone.text = "WRONG"
                        tvDone.setTextColor(Color.parseColor("#FF0000"))
                        wrongCount++
                        Game()
                    }
                }
                //Equals
                findViewById<Button>(R.id.btnEguals).setOnClickListener {
                    if (result1.toInt() == result2.toInt() ){
                        tvDone.text = "CORRECT"
                        tvDone.setTextColor(Color.parseColor("#00FF00"))
                        count++
                        tvScore.text = "$count"
                        Game()
                    }
                    else {
                        tvDone.text = "WRONG"
                        tvDone.setTextColor(Color.parseColor("#FF0000"))
                        wrongCount++
                        Game()
                    }
                }

                //less than
                findViewById<Button>(R.id.btnLess).setOnClickListener {
                    if (result1.toInt() < result2.toInt() ){
                        tvDone.text = "CORRECT"//
                        tvDone.setTextColor(Color.parseColor("#00FF00"))
                        count++
                        tvScore.text = "$count"
                        Game()
                    }
                    else {
                        tvDone.text = "WRONG"
                        tvDone.setTextColor(Color.parseColor("#FF0000"))
                        wrongCount++
                        Game()
                    }
                }
            }
            3 -> {
                val result1 = (num1 - num3) * num4
                tv1.text = "($num1 - $num3)$num4"
                val result2 = (num3 / num2) + num1
                tv2.text = "($num3 / $num2) + $num1"

                //greater than
                findViewById<Button>(R.id.btnGreater).setOnClickListener {
                    if (result1.toInt() > result2.toInt()){
                        tvDone.text = "CORRECT"
                        tvDone.setTextColor(Color.parseColor("#00FF00"))
                        count++
                        tvScore.text = "$count"
                        Game()
                    }
                    else {
                        tvDone.text = "WRONG"
                        tvDone.setTextColor(Color.parseColor("#FF0000"))
                        wrongCount++
                        Game()
                    }
                }
                //Equals
                findViewById<Button>(R.id.btnEguals).setOnClickListener {
                    if (result1.toInt() == result2.toInt() ){
                        tvDone.text = "CORRECT"
                        tvDone.setTextColor(Color.parseColor("#00FF00"))
                        count++
                        tvScore.text = "$count"
                        Game()
                    }
                    else {
                        tvDone.text = "WRONG"
                        tvDone.setTextColor(Color.parseColor("#FF0000"))
                        wrongCount++
                        Game()
                    }
                }

                //less than
                findViewById<Button>(R.id.btnLess).setOnClickListener {
                    if (result1.toInt() < result2.toInt() ){
                        tvDone.text = "CORRECT"
                        tvDone.setTextColor(Color.parseColor("#00FF00"))
                        count++
                        tvScore.text = "$count"
                        Game()
                    }
                    else {
                        tvDone.text = "WRONG"
                        tvDone.setTextColor(Color.parseColor("#FF0000"))
                        wrongCount++
                        Game()
                    }
                }
            }
            4 -> {
                val result1 = ((num4 / num2) * num4) + num1
                tv1.text = "(($num4 / $num2) * $num4) + $num1"
                val result2 = num1 * num2
                tv2.text = "($num1 * $num2)"

                //greater than
                findViewById<Button>(R.id.btnGreater).setOnClickListener {
                    if (result1.toInt() > result2.toInt()){
                        tvDone.text = "CORRECT"
                        tvDone.setTextColor(Color.parseColor("#00FF00"))
                        count++
                        tvScore.text = "$count"
                        Game()
                    }
                    else {
                        tvDone.text = "WRONG"
                        tvDone.setTextColor(Color.parseColor("#FF0000"))
                        wrongCount++
                        Game()
                    }
                }
                //Equals
                findViewById<Button>(R.id.btnEguals).setOnClickListener {
                    if (result1.toInt() == result2.toInt() ){
                        tvDone.text = "CORRECT"
                        tvDone.setTextColor(Color.parseColor("#00FF00"))
                        count++
                        tvScore.text = "$count"
                        Game()
                    }
                    else {
                        tvDone.text = "WRONG"
                        tvDone.setTextColor(Color.parseColor("#FF0000"))
                        wrongCount++
                        Game()
                    }
                }

                //less than
                findViewById<Button>(R.id.btnLess).setOnClickListener {
                    if (result1.toInt() < result2.toInt() ){
                        tvDone.text = "CORRECT"
                        tvDone.setTextColor(Color.parseColor("#00FF00"))
                        count++
                        tvScore.text = "$count"
                        Game()
                    }
                    else {
                        tvDone.text = "WRONG"
                        tvDone.setTextColor(Color.parseColor("#FF0000"))
                        wrongCount++
                        Game()
                    }
                }
            }


        }



    }
}