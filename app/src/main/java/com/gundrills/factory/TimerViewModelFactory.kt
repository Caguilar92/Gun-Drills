package com.gundrills.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gundrills.view_models.TimerViewModel

class TimerViewModelFactory(
        private var defaultDeflection:Int,
        private var defaultCharge:Int,
        private var defaultRefer:Int,
        private var defaultElevation: Int,
        private var defaultChangeButtonText: String,
        private var largeIsSelected:Boolean,
        private var smallIsSelected:Boolean) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimerViewModel::class.java)) {
            return TimerViewModel(
                defaultDeflection,
                defaultCharge,
                defaultElevation,
                defaultRefer,
                defaultChangeButtonText,
                largeIsSelected,
                smallIsSelected
            ) as T
        }
        throw IllegalArgumentException("StudyViewModel Class Not Found")
    }
}