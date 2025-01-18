package com.rm.constiquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Start : AppCompatActivity() {

    lateinit var btnStart : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btnStart = findViewById(R.id.btnStart)



        val sharedPreferences = getSharedPreferences("QuizPreferences", MODE_PRIVATE)

        val completed = sharedPreferences.getString("completed", "0") // Default to "0" if not found

// Check if the value of 'completed' is "1"
        if (completed == "1") {
            // The quiz is completed
            val intent = Intent(this, Result::class.java)
            startActivity(intent)
            Toast.makeText(this, "Quiz completed!", Toast.LENGTH_SHORT).show()
        } else {
            // The quiz is not completed
            Toast.makeText(this, "Quiz not completed yet.", Toast.LENGTH_SHORT).show()
        }


        btnStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}