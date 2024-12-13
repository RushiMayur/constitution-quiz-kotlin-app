package com.rm.constiquiz

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class QuestionAdapter(private val questions: List<DataClassQuestion>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {


    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.ViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.quiz_layout,parent,false)

        context=parent.context

        return QuestionAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questions[position]

        holder.questionText.text = question.questionText.toString()

        var selected = "nil"

        if (question.option1.isEmpty()) {
            holder.option1.visibility = GONE
        }
        if (question.option2.isEmpty()) {
            holder.option2.visibility = GONE
        }
        if (question.option3.isEmpty()) {
            holder.option3.visibility = GONE
        }
        if (question.option4.isEmpty()) {
            holder.option4.visibility = GONE
        }


        holder.option1.text = question.option1.toString()
        holder.option2.text = question.option2.toString()
        holder.option3.text = question.option3.toString()
        holder.option4.text = question.option4.toString()


        holder.option1.setOnClickListener {
            selected = "1"
            holder.btnSubmit.visibility = VISIBLE

            holder.option2.setBackgroundColor(Color.parseColor("#D99CFF"))
            holder.option3.setBackgroundColor(Color.parseColor("#D99CFF"))
            holder.option4.setBackgroundColor(Color.parseColor("#D99CFF"))

            holder.option1.setBackgroundColor(Color.parseColor("#B223FF"))


        }
        holder.option2.setOnClickListener {
            selected = "2"
            holder.btnSubmit.visibility = VISIBLE

            holder.option1.setBackgroundColor(Color.parseColor("#D99CFF"))
            holder.option3.setBackgroundColor(Color.parseColor("#D99CFF"))
            holder.option4.setBackgroundColor(Color.parseColor("#D99CFF"))

            holder.option2.setBackgroundColor(Color.parseColor("#B223FF"))
        }
        holder.option3.setOnClickListener {
            selected = "3"
            holder.btnSubmit.visibility = VISIBLE

            holder.option1.setBackgroundColor(Color.parseColor("#D99CFF"))
            holder.option2.setBackgroundColor(Color.parseColor("#D99CFF"))
            holder.option4.setBackgroundColor(Color.parseColor("#D99CFF"))

            holder.option3.setBackgroundColor(Color.parseColor("#B223FF"))
        }
        holder.option4.setOnClickListener {
            selected = "4"
            holder.btnSubmit.visibility = VISIBLE

            holder.option1.setBackgroundColor(Color.parseColor("#D99CFF"))
            holder.option2.setBackgroundColor(Color.parseColor("#D99CFF"))
            holder.option3.setBackgroundColor(Color.parseColor("#D99CFF"))

            holder.option4.setBackgroundColor(Color.parseColor("#B223FF"))
        }



        holder.btnSubmit.setOnClickListener {

            holder.option1.isEnabled = false
            holder.option2.isEnabled = false
            holder.option3.isEnabled = false
            holder.option4.isEnabled = false



            if(selected == question.answer){
                holder.btnSubmit.visibility = GONE
                holder.txtExplain.visibility = VISIBLE
                holder.txtExplain.text = "Correct.\n${question.explanation.toString()}"

                if (selected == "1") holder.option1.setBackgroundColor(Color.GREEN)
                else if (selected == "2") holder.option2.setBackgroundColor(Color.GREEN)
                else if (selected == "3") holder.option3.setBackgroundColor(Color.GREEN)
                else if (selected == "4") holder.option4.setBackgroundColor(Color.GREEN)


            }
            else{
                holder.btnSubmit.visibility = GONE
                holder.txtExplain.visibility = VISIBLE
                holder.txtExplain.text = "Wrong.\n${question.explanation.toString()}"

                if (selected == "1") holder.option1.setBackgroundColor(Color.RED)
                else if (selected == "2") holder.option2.setBackgroundColor(Color.RED)
                else if (selected == "3") holder.option3.setBackgroundColor(Color.RED)
                else if (selected == "4") holder.option4.setBackgroundColor(Color.RED)

                if (question.answer == "1") holder.option1.setBackgroundColor(Color.GREEN)
                else if (question.answer == "2") holder.option2.setBackgroundColor(Color.GREEN)
                else if (question.answer == "3") holder.option3.setBackgroundColor(Color.GREEN)
                else if (question.answer == "4") holder.option4.setBackgroundColor(Color.GREEN)
            }



        }


    }

    override fun getItemCount(): Int = questions.size

    // ViewHolder class to bind data to the item view
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
         val questionText: TextView = itemView.findViewById(R.id.txtQuestion)

         val option1: Button = itemView.findViewById(R.id.btnOpt1)
         val option2: Button = itemView.findViewById(R.id.btnOpt2)
         val option3: Button = itemView.findViewById(R.id.btnOpt3)
         val option4: Button = itemView.findViewById(R.id.btnOpt4)

        val btnSubmit: Button = itemView.findViewById(R.id.btnSubmit)

        val txtExplain: TextView = itemView.findViewById(R.id.txtExplain)



    }
}
