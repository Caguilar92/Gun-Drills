package com.gundrills.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gundrills.view_models.StopWatchViewModel

class StopWatchViewModelFactory(
        private var defaultDeflection:Int,
        private var defaultCharge:Int,
        private var defaultRefer:Int,
        private var defaultElevation: Int,
        private var defaultChangeButtonText: String,
        private var largeIsSelected:Boolean,
        private var smallIsSelected:Boolean) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StopWatchViewModel::class.java)) {
            return StopWatchViewModel(
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