package com.rm.constiquiz

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var btnStart: Button
    lateinit var btnNext: Button
    lateinit var txtwait: TextView
    lateinit var parentLayout: RelativeLayout
    lateinit var rvQuiz:RecyclerView
    private lateinit var questionAdapter: QuestionAdapter
    private val questionsList = mutableListOf<DataClassQuestion>()

    var already = "nil"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        btnNext = findViewById(R.id.btnNext)
        txtwait = findViewById(R.id.txtWait)
        parentLayout = findViewById(R.id.parentLayout)
        rvQuiz = findViewById(R.id.rvQuiz)


        // Set up RecyclerView
        rvQuiz.layoutManager = LinearLayoutManager(this)
        questionAdapter = QuestionAdapter(questionsList)
        rvQuiz.adapter = questionAdapter




        btnStart.setOnClickListener {
            rvQuiz.visibility =VISIBLE
            btnStart.text = "NEXT"
            txtwait.visibility = VISIBLE
            //btnNext.visibility = VISIBLE


            if(already == "nil"){
                generateNewQuestion("")
            }
            else{
                generateNewQuestion("These questions are already asked, so don't ask them again : $already")
            }

        }

        btnNext.setOnClickListener {
            rvQuiz.visibility =VISIBLE
            txtwait.visibility = VISIBLE

            if(already == "nil"){
                generateNewQuestion("")
            }
            else{
                generateNewQuestion("These questions are already asked, so don't ask them again : $already")
            }

        }


    }

    private fun generateNewQuestion(already1 : String) {
        val text1 = "Make a random question for a quiz regarding Indian constitution from your knowledge. Give four options, each option separated with #. Then give correct answer number 1 or 2 or 3 or 4 at the end after another #. Then give one or 2 sentence explanation for the answer after another #. Don't give anything extra, and don't make newline" +
                " It should be in this format - 'question#option1#option2#option3#option4#answerNumber#explanation'. " + already1
        Log.d("Quest", "Q: $text1")
        val textToGenerate = text1
        var output = ""
        var final = ""

        val task = GoogleCloudGenerativeLanguageTask(this) { result ->
            output = result

            try {
                val jsonObject = JSONObject(output)
                val candidatesArray = jsonObject.getJSONArray("candidates")
                if (candidatesArray.length() > 0) {
                    val firstCandidate = candidatesArray.getJSONObject(0)
                    val content = firstCandidate.getJSONObject("content")
                    val partsArray = content.getJSONArray("parts")

                    if (partsArray.length() > 0) {
                        val firstPart = partsArray.getJSONObject(0)
                        final = firstPart.getString("text")

                        val parts = final.split("#").map { it.trim() }

                        if (parts.size >= 7) {
                            val question = parts[0]
                            val option1 = parts[1]
                            val option2 = parts[2]
                            val option3 = parts[3]
                            val option4 = parts[4]
                            val answer = parts[5]
                            val explanation = parts[6]
                            // Log the question and options

                            already  = "$already. $question"

                            Log.d("XYZ", "Question: $question")
                            Log.d("XYZ", "Option 1: $option1")
                            Log.d("XYZ", "Option 2: $option2")
                            Log.d("XYZ", "Option 3: $option3")
                            Log.d("XYZ", "Option 4: $option4")
                            Log.d("XYZ", "Answer : $answer")
                            Log.d("XYZ", "Explanation : $explanation")
                            // Add the new question to the list

                            val newQuestion = DataClassQuestion(question, option1, option2, option3, option4, answer, explanation)
                            questionsList.add(newQuestion)

                            // Notify the adapter about the new question
                            questionAdapter.notifyItemInserted(questionsList.size - 1)
                            rvQuiz.scrollToPosition(questionsList.size - 1)

                            //btnNext.visibility= VISIBLE
                            txtwait.visibility = GONE

                        } else {
                            Log.e("XYZ", "Failed to parse question and options correctly.")
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("XYZ", "Error parsing JSON: ${e.message}")
                Toast.makeText(this, "Error parsing JSON!", Toast.LENGTH_SHORT).show()
            }
        }

        task.execute(textToGenerate)
    }
}
