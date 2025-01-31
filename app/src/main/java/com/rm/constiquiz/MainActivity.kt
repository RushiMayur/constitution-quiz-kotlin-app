package com.rm.constiquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.util.Linkify
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var btnStart: Button
    lateinit var btnNext: Button
    lateinit var btnEnd: AppCompatButton
    lateinit var btnNextSection:Button
    lateinit var txtwait: TextView
    lateinit var parentLayout: RelativeLayout
    lateinit var rvQuiz:RecyclerView


    lateinit var llTop : LinearLayout
    lateinit var txtTitle : TextView
    lateinit var txtDes : TextView
    lateinit var txtLink : TextView
    lateinit var btnStudied : AppCompatButton




    private lateinit var questionAdapter: QuestionAdapter
    private val questionsList = mutableListOf<DataClassQuestion>()

    var doubleBackToExitPressedOnce = false


    var already1 = "nil"

    var already2 = "nil"

    var already3 = "nil"

    var already4 = "nil"

    var already5 = "nil"


    var a = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        btnNext = findViewById(R.id.btnNext)
        btnEnd = findViewById(R.id.btnEnd)
        btnNextSection = findViewById(R.id.btnNextSection)
        txtwait = findViewById(R.id.txtWait)
        parentLayout = findViewById(R.id.parentLayout)
        rvQuiz = findViewById(R.id.rvQuiz)

        llTop = findViewById(R.id.llTop)
        txtTitle = findViewById(R.id.txtTitle)
        txtDes = findViewById(R.id.txtDes)
        txtLink = findViewById(R.id.txtLink)
        btnStudied = findViewById(R.id.btnStudied)



        // Set up RecyclerView
        rvQuiz.layoutManager = LinearLayoutManager(this)
        questionAdapter = QuestionAdapter(questionsList)
        rvQuiz.adapter = questionAdapter




        val text1 = "Make a random question for a quiz regarding Indian constitution from your knowledge. Give four options, each option separated with #. Then give correct answer number 1 or 2 or 3 or 4 at the end after another #. Then give one or 2 sentence explanation for the answer after another #. Don't give anything extra, and don't make newline. Also don't use any apostrophe or double quotes anywhere." +
                " It should be in this format - 'question#option1#option2#option3#option4#answerNumber#explanation'. "
        val text2 = "Make a random question for a quiz regarding American constitution from your knowledge. Give four options, each option separated with #. Then give correct answer number 1 or 2 or 3 or 4 at the end after another #. Then give one or 2 sentence explanation for the answer after another #. Don't give anything extra, and don't make newline. Also don't use any apostrophe or double quotes anywhere." +
                " It should be in this format - 'question#option1#option2#option3#option4#answerNumber#explanation'. "
        val text3 = "Make a random question for a quiz regarding Movies from your knowledge. Give four options, each option separated with #. Then give correct answer number 1 or 2 or 3 or 4 at the end after another #. Then give one or 2 sentence explanation for the answer after another #. Don't give anything extra, and don't make newline. Also don't use any apostrophe or double quotes anywhere." +
                " It should be in this format - 'question#option1#option2#option3#option4#answerNumber#explanation'. "
        val text4 = "Make a random question for a quiz regarding smart phones from your knowledge. Give four options, each option separated with #. Then give correct answer number 1 or 2 or 3 or 4 at the end after another #. Then give one or 2 sentence explanation for the answer after another #. Don't give anything extra, and don't make newline. Also don't use any apostrophe or double quotes anywhere." +
                " It should be in this format - 'question#option1#option2#option3#option4#answerNumber#explanation'. "
        val text5 = "Make a random question for a quiz regarding animals from your knowledge. Give four options, each option separated with #. Then give correct answer number 1 or 2 or 3 or 4 at the end after another #. Then give one or 2 sentence explanation for the answer after another #. Don't give anything extra, and don't make newline. Also don't use any apostrophe or double quotes anywhere." +
                " It should be in this format - 'question#option1#option2#option3#option4#answerNumber#explanation'. "



        val title1 = "Indian Constitution"
        val des1 = "The Indian Constitution is the supreme law of India, laying the foundation for governance, rights, and duties of citizens."
        val link1 = "https://en.wikipedia.org/wiki/Constitution_of_India"

        val title2 = "American Constitution"
        val des2 = "The American Constitution is the fundamental law of the United States, outlining the nation's government structure and citizens' rights."
        val link2 = "https://en.wikipedia.org/wiki/Constitution_of_the_United_States"

        val title3 = "About Movies"
        val des3 = "Movies are a form of visual storytelling that entertain, educate, and inspire audiences worldwide through various genres and themes."
        val link3 = "https://www.imdb.com/"

        val title4 = "About Smart Phones"
        val des4 = "Smartphones are handheld devices that combine communication, computing, and entertainment, revolutionizing modern digital lifestyles."
        val link4 = "https://en.wikipedia.org/wiki/Smartphone"

        val title5 = "About Animals"
        val des5 = "Animals are living organisms that inhabit diverse ecosystems, playing crucial roles in nature and coexisting with humans in various ways."
        val link5 = "https://en.wikipedia.org/wiki/Animal"



        txtTitle.text = title1
        txtDes.text = des1
        txtLink.text = link1

        Linkify.addLinks(txtLink, Linkify.WEB_URLS)
        txtLink.isClickable = true
        txtLink.isFocusable = true






        val sharedPreferences = getSharedPreferences("QuizPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

// Initialize variables
        for (i in 1..5) {
            for (j in 1..3) {
                editor.putString("q${i}${j}", "0") // Example: q11, q12, ..., q53
            }
        }
        editor.putString("section", "1")  // Current section
        editor.putString("completed", "0") // Quiz completion status

        editor.apply()





        btnStudied.setOnClickListener {
            llTop.visibility = GONE
            btnStart.visibility = VISIBLE
            btnStart.text = "START"
            //btnNext.visibility = VISIBLE

        }







        btnStart.setOnClickListener {
            rvQuiz.visibility =VISIBLE
            btnStart.text = "NEXT"
            txtwait.visibility = VISIBLE
            //btnNext.visibility = VISIBLE
            var already = "xxx"
            var text = "yyy"
            if(a == 1){
                already = already1
                text = text1
            }
            else if(a == 2){
                already = already2
                text = text2
            }
            else if(a == 3){
                already = already3
                text = text3
            }
            else if(a == 4){
                already = already4
                text = text4
            }
            else if(a == 5){
                already = already5
                text = text5
            }

            val sharedPreferences1 = getSharedPreferences("QuizPreferences", MODE_PRIVATE)
            val completed1 = sharedPreferences1.getString("completed", "0") // Default to "0" if not found

// Check if the value of 'completed' is "1"
            if (completed1 == "1") {
                val intent = Intent(this, Result::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            }
            else if(already == "xxx" || text == "yyy"){
                Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show()
            }

            else{
                generateNewQuestion(already, text)
            }

        }

        btnNext.setOnClickListener {
            rvQuiz.visibility =VISIBLE
            txtwait.visibility = VISIBLE
            var already = "xxx"
            var text = "yyy"
            if(a == 1){
                already = already1
                text = text1
            }
            else if(a == 2){
                already = already2
                text = text2
            }
            else if(a == 3){
                already = already3
                text = text3
            }
            else if(a == 4){
                already = already4
                text = text4
            }
            else if(a == 5){
                already = already5
                text = text5
            }

            val sharedPreferences1 = getSharedPreferences("QuizPreferences", MODE_PRIVATE)
            val completed1 = sharedPreferences1.getString("completed", "0") // Default to "0" if not found

// Check if the value of 'completed' is "1"
            if (completed1 == "1") {
                val intent = Intent(this, Result::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            }
            else if(already == "xxx" || text == "yyy"){
                Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show()
            }

            else{
                generateNewQuestion(already, text)
            }

        }




        btnNextSection.setOnClickListener {



            val sharedPreferences1 = getSharedPreferences("QuizPreferences", MODE_PRIVATE)

            val completed1 = sharedPreferences1.getString("completed", "0") // Default to "0" if not found

// Check if the value of 'completed' is "1"
            if (completed1 == "1") {
                val intent = Intent(this, Result::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            }
            else {
                a++
                // Increment the section value
                val currentSection = sharedPreferences1.getString("section", "1")!!.toInt() // Get current section
                val newSection = currentSection + 1 // Increment section
                val editor1 = sharedPreferences1.edit()
                editor1.putString("section", newSection.toString()) // Save the new section
                editor1.apply()

                btnNext.visibility = GONE
                btnStart.visibility = GONE
                llTop.visibility = VISIBLE


                if (newSection in 1..5) {
                    when (newSection) {
                        1 -> {
                            txtTitle.text = title1
                            txtDes.text = des1
                            txtLink.text = link1
                        }
                        2 -> {
                            txtTitle.text = title2
                            txtDes.text = des2
                            txtLink.text = link2
                        }
                        3 -> {
                            txtTitle.text = title3
                            txtDes.text = des3
                            txtLink.text = link3
                        }
                        4 -> {
                            txtTitle.text = title4
                            txtDes.text = des4
                            txtLink.text = link4
                        }
                        5 -> {
                            txtTitle.text = title5
                            txtDes.text = des5
                            txtLink.text = link5
                        }
                    }
                }


                Linkify.addLinks(txtLink, Linkify.WEB_URLS)
                txtLink.isClickable = true
                txtLink.isFocusable = true

                // Clear the list and update the adapter for the next section
                questionsList.clear() // Clear the list
                questionAdapter.notifyDataSetChanged()

                Toast.makeText(this, "Moving to Section $newSection!", Toast.LENGTH_SHORT).show()

                // Adjust button visibility
                //btnStart.visibility = VISIBLE
                btnNextSection.visibility = GONE
            }




        }



        btnEnd.setOnClickListener {





            val alterDialog = AlertDialog.Builder(this)
            alterDialog.setTitle("End Quiz")
            alterDialog.setMessage("Are you sure to end this quiz?")
            alterDialog.setPositiveButton("Submit") { _, _ ->



                val sharedPreferences1 = getSharedPreferences("QuizPreferences", MODE_PRIVATE)
                val editor1 = sharedPreferences1.edit()
                editor1.putString("completed", "1") // Set completed to "1"
                editor1.apply()


                val intent = Intent(this, Result::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

                //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)

            }
            alterDialog.setNegativeButton("No"){ _, _ ->


            }
            alterDialog.create()
            alterDialog.show()








        }









    }

    private fun generateNewQuestion(alreadyx : String, text :String) {
        var textx = text

        if(alreadyx !="nil"){
            textx = text + "These questions are already asked, so don't ask the following :" + alreadyx
        }

        Log.d("Quest", "Q: $textx")
        val textToGenerate = textx.replace("'", "").replace("\"", "")
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




                            if(alreadyx =="nil"){
                                if(a == 1) already1= question
                                else if(a == 2)  already2= question
                                else if(a == 3)  already3= question
                                else if(a == 4)  already4= question
                                else if(a == 5)  already5= question
                            }
                            else{
                                if(a == 1) already1= "$alreadyx, $question"
                                else if(a == 2)  already2= "$alreadyx, $question"
                                else if(a == 3)  already3= "$alreadyx, $question"
                                else if(a == 4)  already4= "$alreadyx, $question"
                                else if(a == 5)  already5= "$alreadyx, $question"
                            }



//                            already1  = "$alreadyx. $question"

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


                            if (questionsList.size >= 3) {
                                //a++ // Increment the value of `a`
//                                questionsList.clear() // Clear the list
//                                questionAdapter.notifyDataSetChanged() // Notify adapter about data change

                                val sharedPreferences = getSharedPreferences("QuizPreferences", MODE_PRIVATE)
                                val currentSection = sharedPreferences.getString("section", "1")!!.toInt() // Get current section

                                if (currentSection == 5) {
                                    // Mark the quiz as completed
                                    val editor = sharedPreferences.edit()
                                    editor.putString("completed", "1") // Set completed to "1"
                                    editor.apply()

                                    btnNextSection.visibility = VISIBLE
                                    btnNextSection.setText("Get Results >")
                                    btnNext.visibility = GONE
                                    btnStart.visibility = GONE

                                    // Log all q values
//                                    for (i in 1..5) {
//                                        for (j in 1..3) {
//                                            val questionKey = "q${i}${j}" // Generate keys like q11, q12, ..., q53
//                                            val value = sharedPreferences.getString(questionKey, "0") // Default to "0" if not found
//                                            Log.d("QuizStatus", "Key: $questionKey, Value: $value")
//                                        }
//                                    }
                                    Toast.makeText(this, "All sections completed!", Toast.LENGTH_SHORT).show()
                                } else {
                                    // Proceed to the next section

                                    btnNext.visibility = GONE
                                    btnStart.visibility = GONE
                                    btnNextSection.visibility  = VISIBLE
                                }





                            }
                            else{
                                btnNextSection.visibility  = GONE
                            }



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


                if(a == 1) already1= "nil"
                else if(a == 2)  already2=  "nil"
                else if(a == 3)  already3=  "nil"
                else if(a == 4)  already4=  "nil"
                else if(a == 5)  already5=  "nil"

            }
        }

        task.execute(textToGenerate)
    }








    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            // If the back button is pressed twice, exit the activity
            super.onBackPressed()
            return
        }

        // If the back button is pressed for the first time, show a Toast
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Current quiz data will be lost. Click back again to exit.", Toast.LENGTH_SHORT).show()

        // Reset the flag after 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            doubleBackToExitPressedOnce = false
        }, 2000) // 2000ms = 2 seconds
    }
}
