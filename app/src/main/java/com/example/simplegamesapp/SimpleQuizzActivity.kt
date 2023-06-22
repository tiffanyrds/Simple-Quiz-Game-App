package com.example.simplegamesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class SimpleQuizzActivity : AppCompatActivity() {

    lateinit var questionsList:ArrayList<QuestionModel>
    private var index:Int = 0
    lateinit var questionModel: QuestionModel

    private var correctAnswerCount:Int=0
    private var wrongAnswerCount:Int=0

    lateinit var countDown: TextView
    lateinit var questions: TextView
    lateinit var option1: Button
    lateinit var option2: Button



    private var backPressedTime: Long = 0
    private var backToast: Toast? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_quizz)
        supportActionBar?.hide()

        countDown=findViewById(R.id.countdown)
        questions=findViewById(R.id.questions)
        option1=findViewById(R.id.option1)
        option2=findViewById(R.id.option2)


        questionsList= ArrayList()
        questionsList.add(QuestionModel("40 x 3 = 120.","True","False","True"))
        questionsList.add(QuestionModel("Roma Ibu kota Italy.","True","False","True"))
        questionsList.add(QuestionModel("Batu adalah material komponen dari matahari.","True","False","False"))
        questionsList.add(QuestionModel("100 + 235 = 435","True","False","False"))
        questionsList.add(QuestionModel("1 x 30 + 1 = 31 .", "True","False","True"))
        questionsList.add(QuestionModel("5 x 30 + 1 = 152 .", "True","False","False"))


        questionModel= questionsList[index]

        setAllQuestions()

        countdown()


    }

    private fun setAllQuestions() {
        questions.text=questionModel.question
        option1.text=questionModel.option1
        option2.text=questionModel.option2
    }

    fun countdown() {
        var duration: Long = TimeUnit.SECONDS.toMillis(5)


        object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                var sDuration: String = String.format(
                    Locale.ENGLISH,
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                    )
                )

                countDown.text = sDuration

            }

            override fun onFinish() {
                index++
                if (index < questionsList.size) {
                    questionModel = questionsList[index]
                    setAllQuestions()
                    resetBackground()
                    enableButton()
                    countdown()

                } else {

                    gameResult()

                }
            }

        }.start()

    }

    private fun gameResult(){
        var intent= Intent(this,ResultActivity::class.java)

        intent.putExtra("correct",correctAnswerCount.toString())
        intent.putExtra("total",questionsList.size.toString())

        startActivity(intent)
    }
    private fun resetBackground(){
        option1.background=resources.getDrawable(R.drawable.option_bg)
        option2.background=resources.getDrawable(R.drawable.option_bg)
    }
    private fun enableButton(){
        option1.isClickable=true
        option2.isClickable=true
    }
    private fun disableButton(){
        option1.isClickable=false
        option2.isClickable=false
    }
    private fun correctAns(option: Button){
        option.background=resources.getDrawable(R.drawable.right_bg)
        correctAnswerCount++
    }

    private fun wrongAns(option:Button){
        option.background=resources.getDrawable(R.drawable.wrong_bg)
        wrongAnswerCount++
    }





    fun option1Clicked(view: View){
        disableButton()
        if(questionModel.option1==questionModel.answer){
            option1.background=resources.getDrawable(R.drawable.right_bg)
            correctAns(option1)
        }
        else{
            wrongAns(option1)
        }
    }
    fun option2Clicked(view:View){
        disableButton()
        if(questionModel.option2==questionModel.answer){
            option2.background=resources.getDrawable(R.drawable.right_bg)
            correctAns(option2)
        }
        else{
            wrongAns(option2)
        }
    }
    override fun onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast?.cancel()
            finish()
        }

        else {
            backToast = Toast.makeText(baseContext, "DOUBLE PRESS TO QUIT GAME", Toast.LENGTH_SHORT)
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()

    }

}