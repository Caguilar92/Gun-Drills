package com.gundrills.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gundrills.view_models.StudyViewModel
import com.gundrills.view_models.TimerViewModel

class StudyViewModelFactory(private var systemId:Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudyViewModel::class.java)) {
            return StudyViewModel(systemId) as T
        }
        throw IllegalArgumentException("StudyViewModel Class Not Found")
    } 
    
}
   