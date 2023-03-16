package com.gundrills.model

data class QuestionsModel(
        val questionNumber: Int,
        val question: String,
        val answer: String,
        val reference: Int,
        val page: Int
)
