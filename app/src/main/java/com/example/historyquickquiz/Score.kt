package com.example.historyquickquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class Score : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        // UI Elements
        val scoreTextView = findViewById<TextView>(R.id.score)
        val feedbackMessageTextView = findViewById<TextView>(R.id.feedbacktxt)
        val reviewButton = findViewById<Button>(R.id.Reviewbtn)
        val exitButton = findViewById<Button>(R.id.Exitbtn2)

        // Get the score from the Intent
        val userScore = intent.getIntExtra("score", 0)

        // Display the score
        val totalQuestions = Quiz.questions.size
        scoreTextView.text = "Your score is: $userScore/$totalQuestions"

        // Determine and display feedback message
        val feedbackMessage = if (userScore >= 3) {
            "Well done!"
        } else {
            "Keep practicing!"
        }
        feedbackMessageTextView.text = feedbackMessage

        // Review button
        reviewButton.setOnClickListener {
            val reviewIntent = Intent(this, Review::class.java)
            reviewIntent.putExtra("Questions", Quiz.questions)
            reviewIntent.putExtra("Answers", Quiz.answers)
            startActivity(reviewIntent)
        }

        // Exit button
        exitButton.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }
}