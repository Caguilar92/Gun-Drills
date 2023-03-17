package com.gundrills.views

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gundrills.R
import com.gundrills.factory.TimerViewModelFactory
import com.gundrills.view_models.TimerViewModel


class StopWatchFragment : Fragment(), View.OnClickListener {
    private var defaultDeflection: Int = 3200
    private var defaultCharge: Int = 0
    private var defaultRefer: Int = 3200
    private var defaultElevation: Int = 1100
    private var defaultChangeButtonText: String = "Change"


    private lateinit var largeDefButton: Button
    private lateinit var smallDefButton: Button
    private lateinit var changeButton: Button
    private lateinit var referButton: Button
    private lateinit var switch:SwitchCompat


    private var start: String = "Start"
    private var stop: String = "Stop"
    private var change: String = "Change"
    private lateinit var timer: Chronometer


    private lateinit var deflectionView: TextView
    private lateinit var chargeView: TextView
    private lateinit var elevationView: TextView
    private lateinit var viewModel: TimerViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_stopwatch, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            TimerViewModelFactory(
                defaultDeflection,
                defaultCharge,
                defaultRefer,
                defaultElevation,
                defaultChangeButtonText,
                false,
                true
            )
        ).get("timerViewModel", TimerViewModel::class.java)
        switch = view.findViewById(R.id.switchCompat)
        largeDefButton = view.findViewById(R.id.large_deflection_button)
        smallDefButton = view.findViewById(R.id.small_deflection_button)
        changeButton = view.findViewById(R.id.change_button)
        referButton = view.findViewById(R.id.refer_deflection_button)
        deflectionView = view.findViewById(R.id.deflection_view)
        chargeView = view.findViewById(R.id.chargeView)
        elevationView = view.findViewById(R.id.elevationView)
        timer = view.findViewById(R.id.chronometer)
        timer.text="00:00"

        setButtonOnclickListeners(largeDefButton, smallDefButton, changeButton, referButton)

        viewModel.getChangeButtonText().observe(viewLifecycleOwner) {
            changeButton.text = it
        }


        viewModel.getSmallIsSelected().observe(viewLifecycleOwner) {
            smallDefButton.isSelected = it
        }

        viewModel.getLargeIsSelected().observe(viewLifecycleOwner) {
            largeDefButton.isSelected = it
        }

        viewModel.getCharge().observe(viewLifecycleOwner) {
            chargeView.text = it.toString()
        }

        viewModel.getElevation().observe(viewLifecycleOwner) {
            if(it < 1000) {
                elevationView.text = "0${it}"
            } else{
                elevationView.text = "$it"
            }

        }

        viewModel.getDeflection().observe(viewLifecycleOwner) {
            if (it < 1000) {
                deflectionView.text = "0${it}"
            } else {
                deflectionView.text = "$it"
            }

        }



        if(viewModel.getChronometerBase() != null) {
            timer.base = viewModel.getChronometerBase()!!
            if(viewModel.isRunning()) {
                timer.start()
            } else {
               timer.text = viewModel.getTimedStopped()
            }
        }







    }


    override fun onClick(button: View?) {

        if (button != null) {

            when (button.id) {
                R.id.change_button -> {
                    button as TextView
                    when (button.text) {
                        start -> {

                            viewModel.changeButtonText(stop)
                            var base = SystemClock.elapsedRealtime()
                            viewModel.setChronomterBase(base)
                            timer.base = base
                            viewModel.isRunning(true)
                            timer.start()

                        }
                        stop -> {

                            viewModel.changeButtonText(change)
                            timer.stop()
                            var time =  (SystemClock.elapsedRealtime() - timer.base)
                            viewModel.setTimeStopped(timer.text.toString())
                            viewModel.isRunning(false)

                            if(switch.isChecked) {
                                Toast.makeText(requireActivity(),"saved",Toast.LENGTH_SHORT).show()
                                if(largeDefButton.isSelected) {
                                    viewModel.addLargeTime(time)
                                } else {
                                    viewModel.addSmallTime(time)
                                }
                            }






                        }

                        change -> {
                            viewModel.changeButtonText(start)
                            viewModel.changeDeflection()
                            viewModel.changeCharge()
                            timer.text = "00:00"


                        }
                    }
                }

                R.id.refer_deflection_button -> {
                    viewModel.changeRefer()
                    viewModel.changeCharge()
                    viewModel.setElevation(1100)
                }

                R.id.large_deflection_button -> {
                    viewModel.largeIsSelected()
                }

                R.id.small_deflection_button -> {
                    viewModel.smallIsSelected()
                }


            }


        }
    }


    private fun setButtonOnclickListeners(
        largeDefButton: Button,
        smallDefButton: Button,
        changeButton: Button,
        referButton: Button
    ) {

        largeDefButton.setOnClickListener(this)
        smallDefButton.setOnClickListener(this)
        changeButton.setOnClickListener(this)
        referButton.setOnClickListener(this)
    }




}