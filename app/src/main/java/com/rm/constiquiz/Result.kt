package com.rm.constiquiz

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class Result : AppCompatActivity() {


    lateinit var barChart : BarChart
    lateinit var txtMarks:TextView
    lateinit var btnRestart: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)



        barChart = findViewById(R.id.barChart)
        txtMarks = findViewById(R.id.txtMarks)
        btnRestart = findViewById(R.id.btnRestart)

        var totalSum = 0

        // Access SharedPreferences
        val sharedPreferences = getSharedPreferences("QuizPreferences", MODE_PRIVATE)

        // Retrieve quiz answers from SharedPreferences
        val quizResults = mutableMapOf<String, Int>()
        for (i in 1..5) {
            for (j in 1..3) {
                val key = "q${i}${j}"
                val value = sharedPreferences.getString(key, "0")?.toInt() ?: 0
                quizResults[key] = value


                // Add the value to the total sum
                totalSum += value
            }
        }

        txtMarks.text = "$totalSum / 15"





        // Calculate the scores for each section (1 to 5)
        val sectionScores = IntArray(5) { 0 }
        for ((key, value) in quizResults) {
            val section = key[1].digitToInt() - 1 // Sections are 1, 2, 3, 4, 5
            if (value == 1) {
                sectionScores[section] += 1
            }
        }

        // Prepare data for the BarChart
        val entries = mutableListOf<BarEntry>()
        for (i in sectionScores.indices) {
            entries.add(BarEntry(i.toFloat(), sectionScores[i].toFloat()))
        }

        // Set the dataset for the bar chart
        val dataSet = BarDataSet(entries, "Quiz Scores")
        dataSet.color = ColorTemplate.MATERIAL_COLORS[0] // Set bar color

        val barData = BarData(dataSet)

        // Set data and customize chart
        barChart.data = barData
        barChart.invalidate() // Refresh the chart

        // Customize X-axis labels (Sections 1 to 5)
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(listOf("1", "2", "3", "4", "5"))
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)





        // Customize Y-axis (Marks 0 to 3)
        val yAxis = barChart.axisLeft
        yAxis.axisMaximum = 3f
        yAxis.axisMinimum = 0f
        yAxis.granularity = 1f
        yAxis.setDrawGridLines(true)
        barChart.axisRight.isEnabled = false



        // Show Toast message for debugging
        Toast.makeText(this, "Chart data loaded", Toast.LENGTH_SHORT).show()




        btnRestart.setOnClickListener {

//            val editor = sharedPreferences.edit()
//
//// Initialize variables
//            for (i in 1..5) {
//                for (j in 1..3) {
//                    editor.putString("q${i}${j}", "0") // Example: q11, q12, ..., q53
//                }
//            }
//            editor.putString("section", "1")  // Current section
//            editor.putString("completed", "0") // Quiz completion status
//
//            editor.apply()


            val intent = Intent(this, Start::class.java)
            startActivity(intent)
            finish()

        }




    }
}