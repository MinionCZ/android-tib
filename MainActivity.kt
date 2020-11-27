package com.example.tictactoe

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var playerText:TextView
    var player = if(Random.nextBoolean()) {
        "crosses"
    }else{
        "circles"
    }
    // var player = if(Random.nextBoolean()) "crosses" else "circles"
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            var root = findViewById<LinearLayout>(R.id.root)
            playerText = TextView(applicationContext)
            playerText.text = player
            root.addView(playerText)
            initValues()
            for (i in 0..2){
            root.addView(createRow(i))
        }

    }

    fun createRow(y : Int) : LinearLayout{
        var row = LinearLayout(applicationContext)
        row.orientation = LinearLayout.HORIZONTAL
        var displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        row.minimumWidth = displayMetrics.widthPixels
        for (i in 0..2){
            row.addView(generateButton(i, y))
        }
        row.setPadding(20, 20, 20, 20)
        return row
    }

    fun generateButton(x : Int, y : Int) : Button {
        var button = Button(applicationContext)
        var drawable = GradientDrawable()
        drawable.cornerRadius = 10f
        drawable.setColor(Color.rgb(255,0,0))
        button.background = drawable
        button.width = 150
        button.height = 150
        button.setPadding(20, 20, 20, 20)
        button.setOnClickListener {
            setValue(x, y, player)
            player = if(player == "crosses"){
                button.setBackgroundColor(Color.BLUE)
                "circles"
            }else{
                button.setBackgroundColor(Color.GREEN)
                "crosses"
            }
            playerText.text = player
            button.isClickable = false
            var winner = checkForWinner()
            if (winner != "null"){
            Toast.makeText(applicationContext, "Vítěz je: $winner", Toast.LENGTH_LONG).show()
        }
    }
        return button

    }
}