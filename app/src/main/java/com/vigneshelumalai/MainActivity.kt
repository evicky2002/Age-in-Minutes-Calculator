package com.vigneshelumalai

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSelectDate: Button = findViewById(R.id.btnSelectDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        btnSelectDate.setOnClickListener{
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)
        val currentMonth = myCalendar.get(Calendar.MONTH)
        val currentDay = myCalendar.get(Calendar.DAY_OF_MONTH)
         val dpd = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                val isLeapYear = isLeapYear(currentYear)
                var totalDays = getTotalDays(currentYear,selectedYear)
                var selectedYearExcessDays = 0
                for(i in 1 until selectedMonth+1){
                    println("here : $i")
                    when(i){
                        1 -> selectedYearExcessDays+=31
                        2 -> selectedYearExcessDays += if(isLeapYear) 29 else 28
                        3 -> selectedYearExcessDays+=31
                        4 -> selectedYearExcessDays+=30
                        5 -> selectedYearExcessDays+=31
                        6 -> selectedYearExcessDays+=30
                        7 -> selectedYearExcessDays+=31
                        8 -> selectedYearExcessDays+=31
                        9 -> selectedYearExcessDays+=30
                        10 -> selectedYearExcessDays+=30
                        11 -> selectedYearExcessDays+=30
                        12 -> selectedYearExcessDays+=31
                    }
                }
                selectedYearExcessDays += selectedDayOfMonth
                var currentYearGonePastDays = 0
                for(i in 1 until currentMonth+1){
                    println("here : $i")
                    when(i){
                        1 -> currentYearGonePastDays+=31
                        2 -> currentYearGonePastDays += if(isLeapYear) 29 else 28
                        3 -> currentYearGonePastDays+=31
                        4 -> currentYearGonePastDays+=30
                        5 -> currentYearGonePastDays+=31
                        6 -> currentYearGonePastDays+=30
                        7 -> currentYearGonePastDays+=31
                        8 -> currentYearGonePastDays+=31
                        9 -> currentYearGonePastDays+=30
                        10 -> currentYearGonePastDays+=30
                        11 -> currentYearGonePastDays+=30
                        12 -> currentYearGonePastDays+=31
                    }
                }
                currentYearGonePastDays+=currentDay
                totalDays = if(isLeapYear){
                    val currentYearRemainingDays = 366 - currentYearGonePastDays
                    totalDays - currentYearRemainingDays
                }else{
                    val currentYearRemainingDays = 365 - currentYearGonePastDays
                    totalDays - currentYearRemainingDays

                }
                totalDays -= selectedYearExcessDays
                val ageInMinutes = totalDays*24*60
                tvAgeInMinutes?.text = (ageInMinutes.toString())
                tvSelectedDate?.text = (selectedDate)

            },
            currentYear,
            currentMonth,
            currentDay
            )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
    private fun getTotalDays(currentYear: Int, bornYear: Int): Int {
        var totalDays = 0
        for(i in bornYear..currentYear){
            val isLeapYear = isLeapYear(i)
            totalDays += if(isLeapYear){
                366
            }else{
                365
            }
        }
        return totalDays
    }
    private fun isLeapYear(year: Int): Boolean{
        val isLeapYear = if (year % 400 == 0) {
            true
        } else if (year % 100 == 0) {
            false
        } else year % 4 == 0
        return isLeapYear
    }
}