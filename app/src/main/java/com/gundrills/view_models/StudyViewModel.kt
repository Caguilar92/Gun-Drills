package com.gundrills.view_models


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gundrills.model.Questions
import com.gundrills.model.QuestionsModel


class StudyViewModel(systemId: Int) : ViewModel() {

    private val questionList = Questions.getQestions(systemId)
    private var questionNumber = 0
    private var currentQuestionOBJ:MutableLiveData<QuestionsModel> = MutableLiveData(questionList[questionNumber])




    fun questionObj(): LiveData<QuestionsModel> {
        return currentQuestionOBJ
    }





    fun nextQuestion() {
        if (questionNumber == questionList.size - 1) {
            questionNumber = 0
        } else {
             questionNumber++
        }

        currentQuestionOBJ.value = questionList[questionNumber]


    }

    fun previousQuestion() {
        if (questionNumber == 0) {
            questionNumber = questionList.size - 1
        } else {
            questionNumber--
        }
        currentQuestionOBJ.value = questionList[questionNumber]

    }






    fun getQuestionListSize(): Int {
        return questionList.size
    }


}