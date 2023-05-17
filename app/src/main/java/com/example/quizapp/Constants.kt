package com.example.quizapp

object Constants {

    const val userName : String = "user_name"
    const val totalQuestions : String = "total_questions"
    const val correctAnswers : String = "correct_answers"

    fun getQuestions() : ArrayList<Question> {
        val questionList = ArrayList<Question>()

        val question1 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Italy",
            "India",
            "Germany",
            "Nigeria",
            2
        )
        val question2 = Question(
            2, "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina",
            "Mexico",
            "Canada",
            "Finland",
            1
        )
        val question3 = Question(
            3, "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "United States of America",
            "New Zealand",
            "Austria",
            "Australia",
            4
        )
        val question4 = Question(
            4, "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Germany",
            "Belgium",
            "Scotland",
            "Algeria",
            2
        )
        val question5 = Question(
            5, "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Brazil",
            "Peru",
            "Denmark",
            "Mexico",
            1
        )
        val question6 = Question(
            6, "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Switzerland",
            "United Kingdom",
            "Denmark",
            "Ireland",
            3
        )
        val question7 = Question(
            7, "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "New Zealand",
            "Isle of Man",
            "Cuba",
            "Fiji Islands",
            4
        )
        val question8 = Question(
            8, "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "France",
            "Scotland",
            "Germany",
            "Angola",
            3
        )
        val question9 = Question(
            9, "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Iran",
            "Yemen",
            "Saudi Arabia",
            "Kuwait",
            4
        )
        val question10 = Question(
            10, "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "New Zealand",
            "United Kingdom",
            "Australia",
            "Singapore",
            1
        )
        questionList.add(question1)
        questionList.add(question2)
        questionList.add(question3)
        questionList.add(question4)
        questionList.add(question5)
        questionList.add(question6)
        questionList.add(question7)
        questionList.add(question8)
        questionList.add(question9)
        questionList.add(question10)

        return questionList
    }
}