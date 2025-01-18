package com.rm.constiquiz

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Start : AppCompatActivity() {

    lateinit var btnStart : Button
    lateinit var btnCheckPrevious:AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btnStart = findViewById(R.id.btnStart)
        btnCheckPrevious = findViewById(R.id.btnCheckPrevious)



        val sharedPreferences = getSharedPreferences("QuizPreferences", MODE_PRIVATE)

        val completed = sharedPreferences.getString("completed", "0") // Default to "0" if not found

// Check if the value of 'completed' is "1"
        if (completed == "1") {
            btnCheckPrevious.visibility = VISIBLE
            // The quiz is completed
//            val intent = Intent(this, Result::class.java)
//            startActivity(intent)
//            Toast.makeText(this, "Quiz completed!", Toast.LENGTH_SHORT).show()
        } else {
            // The quiz is not completed
            btnCheckPrevious.visibility = GONE

//            Toast.makeText(this, "Quiz not completed yet.", Toast.LENGTH_SHORT).show()
        }


        btnStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnCheckPrevious.setOnClickListener {



            val sharedPreferences1 = getSharedPreferences("QuizPreferences", MODE_PRIVATE)

            val completed1 = sharedPreferences1.getString("completed", "0") // Default to "0" if not found

// Check if the value of 'completed' is "1"
            if (completed1 == "1") {
                val intent = Intent(this, Result::class.java)
                startActivity(intent)
            } else {
                // The quiz is not completed
                Toast.makeText(this, "Quiz wasn't submitted or Data lost", Toast.LENGTH_SHORT).show()
            }




        }

    }
}