package com.alivecor.fragmenttest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

/**
 * View model for age calculation.
 */
class DetailsViewModel :ViewModel() {
    var ageCalculatedValue: MutableLiveData<String> = MutableLiveData()

    /**
     * Used to get diff in years, month and days.
     */
    fun ageCalculated(dob : String){
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val dateOfBirth = LocalDate.parse(dob, dateTimeFormatter)
        val period: Period = Period.between(dateOfBirth, LocalDate.now())
        ageCalculatedValue.value="${period.years} years, ${period.months} month, ${period.days} days."

    }
}