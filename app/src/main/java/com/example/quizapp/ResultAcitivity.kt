package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ResultAcitivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_acitivity)

        val name : TextView = findViewById(R.id.username)
        val score : TextView = findViewById(R.id.score)
        val again : Button = findViewById(R.id.again)
        val exit : Button = findViewById(R.id.exit)
        val answers : Button = findViewById(R.id.answers)

        val finalMsg : TextView = findViewById(R.id.finalmsg)
        val goldMedal : ImageView = findViewById(R.id.goldMedal)
        val silverMedal : ImageView = findViewById(R.id.silverMedal)
        val bronzeMedal : ImageView = findViewById(R.id.bronzeMedal)
        val sadEmoji : ImageView = findViewById(R.id.sadEmoji)

        val totalQuiz : Int = intent.getIntExtra(Constants.totalQuestions, 0)
        val correct : Int = intent.getIntExtra(Constants.correctAnswers, 0)

        name.text = intent.getStringExtra(Constants.userName)
        score.text = "Your score is $correct out of $totalQuiz"

        var percentage : Double = correct.toDouble()/totalQuiz.toDouble()
        percentage *= 100.0

        when (percentage) {
            100.0 -> {
                Toast.makeText(this, "Perfect score!", Toast.LENGTH_LONG).show()
                goldMedal.visibility = View.VISIBLE
                silverMedal.visibility = View.GONE
                bronzeMedal.visibility = View.GONE
                sadEmoji.visibility = View.GONE
            }
            in 80.0..90.0 -> {
                Toast.makeText(this, "Excellent score!", Toast.LENGTH_LONG).show()
                goldMedal.visibility = View.GONE
                silverMedal.visibility = View.VISIBLE
                bronzeMedal.visibility = View.GONE
                sadEmoji.visibility = View.GONE
            }
            in 50.0..70.0 -> {
                Toast.makeText(this, "Good! but you can still improve", Toast.LENGTH_LONG).show()
                goldMedal.visibility = View.GONE
                silverMedal.visibility = View.GONE
                bronzeMedal.visibility = View.VISIBLE
                sadEmoji.visibility = View.GONE
            }
            else -> {
                Toast.makeText(this, "Ohh! ${intent.getStringExtra(Constants.userName)} you Failed...Go Study!", Toast.LENGTH_LONG).show()
                finalMsg.text = "Ohhh!"
                goldMedal.visibility = View.GONE
                silverMedal.visibility = View.GONE
                bronzeMedal.visibility = View.GONE
                sadEmoji.visibility = View.VISIBLE
            }
        }

        again.setOnClickListener {
            startActivity(Intent(this, QuizQuestionActivity::class.java))
            finish()
        }

        exit.setOnClickListener{
            finish()
        }

        answers.setOnClickListener {
            startActivity(Intent(this, AnswersActivity::class.java))
        }
    }
}