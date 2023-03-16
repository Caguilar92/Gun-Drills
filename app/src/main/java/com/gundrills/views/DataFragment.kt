package com.gundrills.views

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.gundrills.MainActivity
import com.gundrills.R
import com.gundrills.factory.TimerViewModelFactory
import com.gundrills.view_models.StudyViewModel
import com.gundrills.view_models.TimerViewModel



class StatsFragment : Fragment() {

        private  lateinit var viewModel:TimerViewModel
        private lateinit var largeDeflectionAverageTime:TextView
        private lateinit var smallDeflectionAverageTime:TextView
        private lateinit var bestLargeDeflectionTime:TextView
        private lateinit var bestSmallDeflectionTime:TextView
        private lateinit var clearButton: Button
        private val DEFAULT_TIME = "00:00.0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get("timerViewModel",TimerViewModel::class.java)
        largeDeflectionAverageTime = view.findViewById(R.id.largeDeflectionAvgTime)
        smallDeflectionAverageTime = view.findViewById(R.id.smallDeflectionAvgTime)
        bestLargeDeflectionTime = view.findViewById(R.id.bestLargeDeflectionTime)
        bestSmallDeflectionTime = view.findViewById(R.id.bestSmallDeflectionTime)
        clearButton = view.findViewById(R.id.clearButton)

        largeDeflectionAverageTime.text = DEFAULT_TIME
        smallDeflectionAverageTime.text = DEFAULT_TIME


       viewModel.bestLargeTime().observe(viewLifecycleOwner) {
           if(it == Long.MAX_VALUE) {
               bestLargeDeflectionTime.text = DEFAULT_TIME
           }
           else {
               bestLargeDeflectionTime.text = timeToString(it)
           }
       }

        viewModel.bestSmallTime().observe(viewLifecycleOwner) {
            if(it == Long.MAX_VALUE) {
                bestSmallDeflectionTime.text = DEFAULT_TIME
            }
            else {
                bestSmallDeflectionTime.text = timeToString(it)
            }
        }

        viewModel.largeAverage().observe(viewLifecycleOwner) {
            if(it == 0.0) {
                largeDeflectionAverageTime.text = DEFAULT_TIME
            }
            else {
                largeDeflectionAverageTime.text = avgTimeToString(it)
            }
        }

        viewModel.smallAverage().observe(viewLifecycleOwner) {
            if(it == 0.0) {
                smallDeflectionAverageTime.text = DEFAULT_TIME
            }
            else {
                smallDeflectionAverageTime.text = avgTimeToString(it)
            }
        }


        clearButton.setOnClickListener {
            viewModel.clear()
        }







    }



    private fun timeToString(time:Long): String {

        var minutes = ( (time/1000) /60)
        var seconds = ( (time/1000) % 60)
        Log.v("seconds",seconds.toString())
         var stringMinutes = String.format("%02d:%02d", minutes,seconds)


        return stringMinutes

    }

    private fun avgTimeToString(time:Double) : String {
        var minutes = ( (time/1000) /60).toInt()
        var seconds = ( (time/1000) % 60)
        Log.v("seconds",seconds.toString())
        var stringMinutes = String.format("%02d:%02.2f", minutes,seconds)


        return stringMinutes
    }




}