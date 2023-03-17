package com.gundrills.view_models

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class TimerViewModel(deflection: Int, charge: Int, elevation: Int, refer: Int, changButtonText: String,largeIsSelected:Boolean,smallIsSelected:Boolean) : ViewModel() {

    private var deflection: MutableLiveData<Int> = MutableLiveData(deflection)

    private var charge: MutableLiveData<Int> = MutableLiveData(charge)

    private var elevation: MutableLiveData<Int> = MutableLiveData(elevation)

    private var refer: MutableLiveData<Int> = MutableLiveData(refer)

    private var changeButtonText: MutableLiveData<String> = MutableLiveData(changButtonText)

    private var largeIsSelected:MutableLiveData<Boolean> = MutableLiveData(largeIsSelected)
    private var smallIsSelected:MutableLiveData<Boolean> = MutableLiveData(smallIsSelected)


    private var smallDeflectionTimeList:MutableLiveData<MutableList<Long>> = MutableLiveData(ArrayList<Long>())

    private var largeDeflectionTimeList:MutableLiveData<MutableList<Long>> = MutableLiveData(ArrayList<Long>())

    private var bestLargeTime:MutableLiveData<Long> = MutableLiveData(Long.MAX_VALUE)
    private var bestSmallTime:MutableLiveData<Long> = MutableLiveData(Long.MAX_VALUE)
    private var largeAverage:MutableLiveData<Double> = MutableLiveData(0.0)
    private var smallAverage:MutableLiveData<Double> = MutableLiveData(0.0)
    private var totalLarge = 0L
    private var totalSmall = 0L

    private var chronometerBase:Long? = null
    private var isRunning = false
    private var timeStopped:String = "null"



    fun getChronometerBase():Long? {
        return chronometerBase
    }
    fun setChronomterBase(base:Long) {
        chronometerBase = base
    }

    fun isRunning(boolean: Boolean) {
        isRunning = boolean
    }
    fun isRunning():Boolean {
        return isRunning
    }

    fun setTimeStopped(timeStopped:String) {
        this.timeStopped = timeStopped
    }
    fun getTimedStopped():String {
        return this.timeStopped
    }



    fun addLargeTime(time:Long) {
        bestLargeTime.value = minOf(time,bestLargeTime.value!!)

        largeDeflectionTimeList.value?.add(time)
        totalLarge?.plus(time)
        largeAverage.value = largeDeflectionTimeList.value?.average()

    }

    fun largeAverage():MutableLiveData<Double> {
        return largeAverage
    }
    fun smallAverage():MutableLiveData<Double> {
        return smallAverage
    }
    fun addSmallTime(time:Long) {
        bestSmallTime.value = minOf(time,bestSmallTime.value!!)
        smallDeflectionTimeList.value?.add(time)
        totalSmall?.plus(time)
        smallAverage.value = smallDeflectionTimeList.value?.average()

    }

    fun bestLargeTime():MutableLiveData<Long> {
        return bestLargeTime
    }

    fun bestSmallTime():MutableLiveData<Long> {
        return bestSmallTime
    }








    fun getChangeButtonText(): LiveData<String> {

        return changeButtonText
    }

    fun changeButtonText(text:String) {
        changeButtonText.value = text
    }

    fun getLargeIsSelected():LiveData<Boolean> {
        return largeIsSelected
    }

    fun largeIsSelected() {
        largeIsSelected.value = true
        smallIsSelected.value = false
    }

    fun getSmallIsSelected():LiveData<Boolean> {
        return smallIsSelected
    }

    fun smallIsSelected() {
        smallIsSelected.value = true
        largeIsSelected.value = false
    }

    fun getCharge(): LiveData<Int> = charge

    fun changeCharge() {
        val change = Random.Default.nextInt(5)
        charge.value = change
    }

    fun getRefer(): LiveData<Int> = refer

    fun changeRefer() {

        when (refer.value) {
            3200 -> {
                refer.value = 2800
                deflection.value = 2800
            }
            2800 -> {
                refer.value = 700
                deflection.value = 700
            }
            700 -> {
                refer.value = 3200
                deflection.value = 3200
            }
        }
    }

    fun changeDeflection() {
        changeCharge()
        if(smallIsSelected.value == true) {
            smallDeflectionChange()
            smallElevationChange()

        } else {
            largeDeflectionChange()
            largeElevationChange()

        }
    }

    fun smallDeflectionChange() {
        val change = Random.Default.nextInt(20, 60)
        if (deflection.value!! > refer.value!!) {
            deflection.value = deflection.value!!.minus(change)
        } else {
            deflection.value = deflection.value!!.plus(change)
        }


    }

    fun largeDeflectionChange() {
        val change = Random.Default.nextInt(200, 300)
        if (deflection.value!! > refer.value!!) {
            deflection.value = deflection.value!!.minus(change)
        } else {
            deflection.value = deflection.value!!.plus(change)
        }


    }

    fun largeElevationChange() {
        val change = Random.Default.nextInt(100, 200)
        if (elevation.value!! > 1100) {
            elevation.value = elevation.value!!.minus(change)
        } else {
            elevation.value = elevation.value!!.plus(change)
        }
    }

    fun smallElevationChange() {
        val change = Random.Default.nextInt(35, 90)
        if (elevation.value!! > 1100) {
            elevation.value = elevation.value!!.minus(change)
        } else {
            elevation.value = elevation.value!!.plus(change)
        }
    }
    fun getDeflection(): LiveData<Int> = deflection



    fun getElevation(): LiveData<Int> = elevation

    fun setElevation(elev: Int) {
        elevation.value = elev
    }

    fun clear() {
        bestLargeTime.value = Long.MAX_VALUE
        bestSmallTime.value = Long.MAX_VALUE
        largeAverage.value = 0.0
        smallAverage.value = 0.0
        totalLarge = 0L
        totalSmall = 0L
        largeDeflectionTimeList.value?.clear()
        smallDeflectionTimeList.value?.clear()
    }




















}



