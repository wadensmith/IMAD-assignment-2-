package com.example.historyquickquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class Quiz :AppCompatActivity() {

    private lateinit var questiontxt: TextView
    private lateinit var truebtn: Button
    private lateinit var falsebtn: Button
    private lateinit var nextbtn: Button
    private lateinit var feedbacktxt: TextView

    // The quiz questions:
    companion object {
        val questions = arrayOf(
            "Ketchup was once sold as medicine",
            "The shortest war in history lasted 38 min",
            "Cars were invented in the United States",
            "The great wall of China is not the longest man-made structure in the world",
            "President Abraham Lincon's top hat had a purpose"
        )
        val answers = booleanArrayOf(true, true, false, false, true)

    }

    private var currentQuestionIndex = 0
    private var score = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        // Initialize UI elements
        questiontxt = findViewById(R.id.questiontxt)
        truebtn = findViewById(R.id.truebtn)
        falsebtn = findViewById(R.id.falsebtn)
        nextbtn = findViewById(R.id.nextbtn)
        feedbacktxt = findViewById(R.id.feedbacktxt)

        // Display 1st question
        displayQuestion()

        // Answer buttons
        truebtn.setOnClickListener { checkAnswer(true) }
        falsebtn.setOnClickListener { checkAnswer(false) }

        // Next button
        nextbtn.setOnClickListener {
            currentQuestionIndex++

            if (currentQuestionIndex < questions.size) {
                displayQuestion()
                feedbacktxt.text = ""
                truebtn.isEnabled = true
                falsebtn.isEnabled = true
            } else {
                // proceed to score activity
                val intent = Intent(this, Score::class.java)
                // Add score to represent what score a user got
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }
            nextbtn.isEnabled = false // Disable next button until an answer is chosen
        }
        nextbtn.isEnabled = false // Initially disable next button
    }

    private fun displayQuestion() {
        // Access questions via the companion object
        questiontxt.text = questions[currentQuestionIndex]
        // Reset button states for the new question
        truebtn.isEnabled = true
        falsebtn.isEnabled = true
        nextbtn.isEnabled = false // Disable next until an answer is given
        feedbacktxt.text = ""     // Clear previous feedback
    }

    @SuppressLint("SetTextI18n")
    private fun checkAnswer(userAnswer: Boolean) {
        // Access answers via the companion object
        val correctAnswer = answers[currentQuestionIndex]

        if (userAnswer == correctAnswer) {
            feedbacktxt.text = "Correct!"
            feedbacktxt.setTextColor(Color.GREEN)
            score++
        } else {
            feedbacktxt.text = "Incorrect!"
            feedbacktxt.setTextColor(Color.RED)
        }

        truebtn.isEnabled = false
        falsebtn.isEnabled = false
        nextbtn.isEnabled = true // Enable next button to proceed
    }
}

