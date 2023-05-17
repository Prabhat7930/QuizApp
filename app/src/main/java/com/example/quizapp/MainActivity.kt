package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start : Button = findViewById(R.id.btn_start)
        val etName : EditText = findViewById(R.id.enter_name)

        start.setOnClickListener {
            if(etName.text.isEmpty())
                Toast.makeText(this, "Name cannot be Empty!", Toast.LENGTH_SHORT).show()
            else {
                val intent1 = Intent(this, QuizQuestionActivity::class.java)
                intent1.putExtra(Constants.userName, etName.text.toString())
                startActivity(intent1)
                finish()
            }
        }
    }
}