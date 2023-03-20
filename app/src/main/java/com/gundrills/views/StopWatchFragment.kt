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
import com.gundrills.factory.StopWatchViewModelFactory
import com.gundrills.view_models.StopWatchViewModel


class StopWatchFragment : Fragment(), View.OnClickListener {

    private var DEFAULT_DEFLECTION: Int = 3200//Initial deflection to display on app start up
    private var DEFAULT_CHARGE: Int = 0//Initial charge to display on app start up

    /*Initial refer deflection to display on app start up. The changes in deflection are based off of what the current refer deflection is.
    * Refer Deflection is the base deflection that changes are based off of.
    * Example: if refer is 2800, than any change will be either added or subtracted off of 2800, depending on the bounds of the function.
      2800 + 60 = 2860 (new deflection to display) 2800 -60 = 2740(new deflection to display).
      fuction is the the StopWatchViewModelClass*/
    private var DEFAULT_REFER_DEFLECTION: Int = 3200

    private var DEFAULT_ELEVATION: Int = 1100//default elevation to display


    private lateinit var largeDefButton: Button
    private lateinit var smallDefButton: Button
    private lateinit var changeButton: Button
    private lateinit var referButton: Button
    private lateinit var switch: SwitchCompat

    private lateinit var changeButtonText: String
    private lateinit var startText: String
    private lateinit var stopText: String
    private lateinit var changeText: String
    private lateinit var toastMessage: String

    private lateinit var viewModelKey:String

    private lateinit var timer: Chronometer
    private lateinit var deflectionView: TextView
    private lateinit var chargeView: TextView
    private lateinit var elevationView: TextView
    private lateinit var viewModel: StopWatchViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_stopwatch, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /*
        reference string in string.xml file. Fragment needs to be attached to activity before getString() is called in order
        for function to work properly so requireActivity() is required in onViewCreated();
         */
        changeButtonText = requireActivity().getString(R.string.change)
        startText = requireActivity().getString(R.string.start)
        stopText = requireActivity().getString(R.string.stop)
        changeText = requireActivity().getString(R.string.change)
        toastMessage = requireActivity().getString(R.string.save)
        viewModelKey = requireActivity().getString(R.string.timerViewModel)


        /* The ViewModelProvider creates a viewModel given given a factory and stores it in a viewmodel store.*/
        viewModel = ViewModelProvider(
            requireActivity(),//lifecycle owner is the activity that it is running in. viewmodel data will live as long as the scope of the activity.
            StopWatchViewModelFactory(//factory create a viewmodel that an be instantiated using the given constructor arguments.
                DEFAULT_DEFLECTION,
                DEFAULT_CHARGE,
                DEFAULT_REFER_DEFLECTION,
                DEFAULT_ELEVATION,
                changeButtonText,
                false,
                true
            )
        ).get(viewModelKey, StopWatchViewModel::class.java)//key value pair used to reference the view model from the viewmodel store;

        switch = view.findViewById(R.id.switchCompat)
        largeDefButton = view.findViewById(R.id.large_deflection_button)
        smallDefButton = view.findViewById(R.id.small_deflection_button)
        changeButton = view.findViewById(R.id.change_button)
        referButton = view.findViewById(R.id.refer_deflection_button)
        deflectionView = view.findViewById(R.id.deflection_view)
        chargeView = view.findViewById(R.id.chargeView)
        elevationView = view.findViewById(R.id.elevationView)
        timer = view.findViewById(R.id.chronometer)
        timer.text = requireActivity().getString(R.string.timer_default)



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
            if (it < 1000) {
                elevationView.text = "0${it}"
            } else {
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


        /*
        ensures chronometers maintains is current state upon navigation or configuration change.
        */
        if (viewModel.getChronometerBase() != null) {
            timer.base = viewModel.getChronometerBase()!!
            if (viewModel.isRunning()) {
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
                        startText -> {

                            viewModel.changeButtonText(stopText)
                            var base = SystemClock.elapsedRealtime()
                            viewModel.setChronomterBase(base)
                            timer.base = base
                            viewModel.isRunning(true)
                            timer.start()

                        }
                        stopText -> {

                            viewModel.changeButtonText(changeText)
                            timer.stop()
                            var time = (SystemClock.elapsedRealtime() - timer.base)
                            viewModel.setTimeStopped(timer.text.toString())
                            viewModel.isRunning(false)

                            if (switch.isChecked) {
                                Toast.makeText(requireActivity(), toastMessage, Toast.LENGTH_SHORT)
                                    .show()
                                if (largeDefButton.isSelected) {
                                    viewModel.addLargeTime(time)
                                } else {
                                    viewModel.addSmallTime(time)
                                }
                            }


                        }

                        changeText -> {
                            viewModel.changeButtonText(startText)
                            viewModel.changeDeflection()
                            viewModel.changeCharge()
                            timer.text = requireActivity().getString(R.string.timer_default)


                        }
                    }
                }

                R.id.refer_deflection_button -> {
                    viewModel.changeRefer()
                    viewModel.changeCharge()
                    viewModel.setElevation(DEFAULT_ELEVATION)
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