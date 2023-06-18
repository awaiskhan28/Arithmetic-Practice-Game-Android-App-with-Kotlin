package com.example.awaiskhanmathgame

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import javax.script.ScriptEngine
//import javax.script.ScriptEngineManager


class GameActivity : AppCompatActivity() {
    val numbers = (1..10)

    val operators = listOf("+", "-", "*", "/", "(")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        //define the textview
        val tv1 = findViewById<TextView>(R.id.tvExpression1)
        val tv2 = findViewById<TextView>(R.id.tvExpression2)

        //assign text view the expressions
        tv1.text = generateExpression(4)
        tv2.text = generateExpression(3)

       // val mgr = ScriptEngineManager()
        //val engine: ScriptEngine = mgr.getEngineByName("JavaScript")

        //greater than
        findViewById<Button>(R.id.btnGreater).setOnClickListener {
            if (generateExpression(4).toInt() > generateExpression(3).toInt()){
                Toast.makeText(applicationContext, "True", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(applicationContext, "False", Toast.LENGTH_SHORT).show()
            }
        }
        //Equals
        findViewById<Button>(R.id.btnEguals).setOnClickListener {
            if (generateExpression(4).toInt() == generateExpression(3).toInt() ){
                Toast.makeText(applicationContext, "True", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(applicationContext, "False", Toast.LENGTH_SHORT).show()
            }
        }

        //less than
        findViewById<Button>(R.id.btnLess).setOnClickListener {
            if (generateExpression(4).toInt() < generateExpression(3).toInt() ){
                Toast.makeText(applicationContext, "True", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(applicationContext, "False", Toast.LENGTH_SHORT).show()
            }
        }

    }

    //function to generate expressions
    fun generateExpression(values: Int): String{
        val expression = StringBuilder()
        var valueCount = values

        if(values == 1){
            return ""
        }

        while(valueCount > 0){

            if(valueCount < values){
                expression.append(operators.random())
            }

            if (valueCount == 1){
                expression.append(numbers.random())
                break
            }

            expression.append(numbers.random())
            expression.append(operators.random())

            expression.append(numbers.random())


            valueCount -= 2
        }

        //close open brackets
        if(expression.contains("(")){
            val openBrackets = expression.count { c: Char -> c == '(' }
            for(i in openBrackets downTo 1){
                expression.append(")")
            }
        }

        return expression.toString()
    }

    }