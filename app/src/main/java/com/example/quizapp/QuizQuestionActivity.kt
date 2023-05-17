package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var myCurrentPosition : Int = 1
    private var myQuestionList : ArrayList<Question> ? = null
    private var mySelectedOptionPosition : Int = 0
    private var myUserName : String ? = null
    private var myCorrectAnswer : Int = 0

    private var progressBar : ProgressBar ? = null
    private var txtProgress : TextView ? = null
    private var txtQuestion : TextView ? = null
    private var flagImage : ImageView ? = null
    private var submit : Button ? = null
    private var hint : Button ? = null
    private var txtHint : TextView ? = null
    private var hintBulb : ImageView ? = null

    private var optionOne : TextView ? = null
    private var optionTwo : TextView ? = null
    private var optionThree : TextView ? = null
    private var optionFour : TextView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        myUserName = intent.getStringExtra(Constants.userName)

        progressBar = findViewById(R.id.progress)
        txtProgress = findViewById(R.id.txtprogress)
        txtQuestion = findViewById(R.id.txtquestion)
        flagImage = findViewById(R.id.image)
        submit = findViewById(R.id.submit)
        hint = findViewById(R.id.hint)
        txtHint = findViewById(R.id.textHint)
        hintBulb = findViewById(R.id.hintBulb)

        optionOne = findViewById(R.id.optionOne)
        optionTwo = findViewById(R.id.optionTwo)
        optionThree = findViewById(R.id.optionThree)
        optionFour = findViewById(R.id.optionFour)

        optionOne?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)
        submit?.setOnClickListener(this)
        hint?.setOnClickListener(this)

        myQuestionList = Constants.getQuestions()

        questionSet()
        defaultOptionView()
    }

    @SuppressLint("SetTextI18n")
    private fun questionSet() {
        defaultOptionView()
        val question: Question = myQuestionList!![myCurrentPosition - 1]
        progressBar?.progress = myCurrentPosition
        txtProgress?.text = "$myCurrentPosition/${progressBar?.max}"
        txtQuestion?.text = question.question
        flagImage?.setImageResource(question.image)

        optionOne?.text = question.optionOne
        optionTwo?.text = question.optionTwo
        optionThree?.text = question.optionThree
        optionFour?.text = question.optionFour

    }

    private fun defaultOptionView() {
        val option = ArrayList<TextView>()
        optionOne?.let {
            option.add(0, it)
        }
        optionTwo?.let {
            option.add(1, it)
        }
        optionThree?.let {
            option.add(2, it)
        }
        optionFour?.let {
            option.add(3, it)
        }

        for (i in option) {
            i.setTextColor(Color.parseColor("#7a8089"))
            i.typeface = Typeface.DEFAULT
            i.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(text1 : TextView, selectedOptionNum : Int) {
        defaultOptionView()

        mySelectedOptionPosition = selectedOptionNum

        text1.setTextColor(Color.parseColor("#363a43"))
        text1.setTypeface(text1.typeface, Typeface.BOLD)
        text1.background = ContextCompat.getDrawable(this, R.drawable.select_option_bg)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.optionOne -> {
                submit?.text = "SUBMIT"
                optionOne?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.optionTwo -> {
                submit?.text = "SUBMIT"
                optionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.optionThree -> {
                submit?.text = "SUBMIT"
                optionThree?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.optionFour -> {
                submit?.text = "SUBMIT"
                optionFour?.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.submit ->{
                submit?.text = "SUBMIT"
                if (myCurrentPosition == myQuestionList!!.size)
                    submit?.text = "FINISH"

                if (mySelectedOptionPosition == 0) {
                    myCurrentPosition++

                    hintBulb?.visibility = View.INVISIBLE
                    txtHint?.text = ""

                    when {
                        myCurrentPosition <= myQuestionList!!.size ->{
                            questionSet()
                        }
                        else -> {
                            val intent2 = Intent(this, ResultAcitivity::class.java)
                            intent2.putExtra(Constants.userName, myUserName)
                            intent2.putExtra(Constants.correctAnswers, myCorrectAnswer)
                            intent2.putExtra(Constants.totalQuestions, myQuestionList!!.size)
                            startActivity(intent2)
                            finish()
                        }
                    }
                }
                else {
                    val question : Question = myQuestionList!![myCurrentPosition - 1]
                    if (question.correctOption != mySelectedOptionPosition) {
                        when(mySelectedOptionPosition) {
                            1 -> {
                                optionOne?.let {
                                    selectedOptionView(it, mySelectedOptionPosition)
                                    answerCheck(mySelectedOptionPosition, R.drawable.wrong_option_bg)
                                }
                            }
                            2 -> {
                                optionTwo?.let {
                                    selectedOptionView(it, mySelectedOptionPosition)
                                    answerCheck(mySelectedOptionPosition, R.drawable.wrong_option_bg)
                                }
                            }
                            3 -> {
                                optionThree?.let {
                                    selectedOptionView(it, mySelectedOptionPosition)
                                    answerCheck(mySelectedOptionPosition, R.drawable.wrong_option_bg)
                                }
                            }
                            4 -> {
                                optionFour?.let {
                                    selectedOptionView(it, mySelectedOptionPosition)
                                    answerCheck(mySelectedOptionPosition, R.drawable.wrong_option_bg)
                                }
                            }
                        }
                    }
                    else {
                        myCorrectAnswer++
                        answerCheck(question.correctOption, R.drawable.wrong_option_bg)
                    }

                    if (myCurrentPosition < myQuestionList!!.size)
                        submit?.text = "NEXT"

                    mySelectedOptionPosition = 0
                }
            }
            R.id.hint -> {
                hintBulb?.visibility = View.VISIBLE
                giveHint(myCurrentPosition)
            }
        }
    }

    private fun answerCheck(answer : Int, drawableView : Int) {
        when(answer) {
            1 -> {
                optionOne?.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                optionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                optionThree?.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                optionFour?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun giveHint(hint : Int) {
        when(hint) {
            1 -> txtHint?.text = "Taj Mahal"
            2 -> txtHint?.text = "Messi"
            3 -> txtHint?.text = "Kangaroo"
            4 -> txtHint?.text = "Waffles"
            5 -> txtHint?.text = "Christ the Redeemer"
            6 -> txtHint?.text = "Danish"
            7 -> txtHint?.text = "Coral capital of the World"
            8 -> txtHint?.text = "Nazi"
            9 -> txtHint?.text = "Dinar"
            10 -> txtHint?.text = "Kiwi"
        }
    }
}