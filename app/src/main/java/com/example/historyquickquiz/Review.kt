package com.example.historyquickquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class Review : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)

        val restartButton = findViewById<Button>(R.id.restartbtn)
        val exitButton = findViewById<Button>(R.id.Exitbtn3)
        val reviewContentTextView = findViewById<TextView>(R.id.reviewContentTextView) // TextView inside ScrollView

        // Questions and answers are passed
        val questions = intent.getStringArrayExtra("Questions")
        val answers = intent.getBooleanArrayExtra("Answers")

        val reviewTextBuilder = StringBuilder() // Renamed for clarity
        if (questions != null && answers != null && questions.size == answers.size) {
            for (i in questions.indices) {
                reviewTextBuilder.append("${i + 1}. ${questions[i]}\n")
                reviewTextBuilder.append("Answer: ${if (answers[i]) "True" else "False"}\n\n")
            }
            reviewContentTextView.text = reviewTextBuilder.toString() // Set text on the TextView
        } else {
            reviewContentTextView.text = "Error: Could not load review data." // Set error text on the TextView
        }

        // Restart button
        restartButton.setOnClickListener {
            val quizIntent = Intent(this, Quiz::class.java)
            startActivity(quizIntent)
            finish()
        }

        // Exit button
        exitButton.setOnClickListener {
            finishAffinity()
            exitProcess(0)   // Exits the application process
        }
    }
}

class R
