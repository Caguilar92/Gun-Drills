package com.gundrills.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gundrills.R
import com.gundrills.view_models.StopWatchViewModel
import kotlin.math.floor


class StatsFragment : Fragment() {

        private  lateinit var viewModel:StopWatchViewModel
        private lateinit var largeDeflectionAverageTime:TextView
        private lateinit var smallDeflectionAverageTime:TextView
        private lateinit var bestLargeDeflectionTime:TextView
        private lateinit var bestSmallDeflectionTime:TextView
        private lateinit var clearButton: Button
        private val DEFAULT_TIME = "00:00"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_logs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get("timerViewModel",StopWatchViewModel::class.java)
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
                var num1 = 7L
                var num2 = 2L
                bestSmallDeflectionTime.text =  DEFAULT_TIME
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
        var seconds = ( (time/1000) % 60).toInt()
        Log.v("seconds",seconds.toString())
        var stringMinutes = String.format("%02d:%02d", minutes,seconds)


        return stringMinutes
    }




}