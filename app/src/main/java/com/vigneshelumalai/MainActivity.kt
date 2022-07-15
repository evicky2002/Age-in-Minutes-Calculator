package com.vigneshelumalai

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance();
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        println("month: $month")
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                val isLeapyear = isLeapYear(year)
                var totalDays = getTotalDays(year,selectedYear)
                var remDays2 = 0
                for(i in 1 until selectedMonth+1){
                    println("here : $i")
                    when(i){
                        1 -> remDays2+=31
                        2 -> if(isLeapyear) remDays2+=29 else remDays2+=28
                        3 -> remDays2+=31
                        4 -> remDays2+=30
                        5 -> remDays2+=31
                        6 -> remDays2+=30
                        7 -> remDays2+=31
                        8 -> remDays2+=31
                        9 -> remDays2+=30
                        10 -> remDays2+=30
                        11 -> remDays2+=30
                        12 -> remDays2+=31
                    }
                }
                remDays2 += selectedDayOfMonth
                var remDays = 0
                for(i in 1 until month+1){
                    println("here : $i")
                    when(i){
                        1 -> remDays+=31
                        2 -> if(isLeapyear) remDays+=29 else remDays+=28
                        3 -> remDays+=31
                        4 -> remDays+=30
                        5 -> remDays+=31
                        6 -> remDays+=30
                        7 -> remDays+=31
                        8 -> remDays+=31
                        9 -> remDays+=30
                        10 -> remDays+=30
                        11 -> remDays+=30
                        12 -> remDays+=31
                    }
                }
                remDays+=day
                if(isLeapyear){
                    var temp = 366 - remDays
                    totalDays = totalDays - temp
                }else{
                    var temp = 365 - remDays
                    totalDays = totalDays - temp

                }
                totalDays-=remDays2
                println("here : $remDays2")
                println("here : $totalDays")
                println("here : $remDays")
                val ageInMinutes = totalDays*24*60
                tvAgeInMinutes?.setText(ageInMinutes.toString())
                tvSelectedDate?.setText(selectedDate)

            },
            year,
            month,
            day
            ).show()
    }
    fun getTotalDays(currentYear: Int, bornYear: Int): Int {
        var totalDays = 0
        for(i in bornYear..currentYear){
            var isLeapYear = isLeapYear(i)
            if(isLeapYear){
                totalDays+=366
            }else{
                totalDays+=365
            }

        }
        return totalDays
    }
    fun isLeapYear(year: Int): Boolean{
        var isLeapYear = false
        if (year % 400 == 0) {
            isLeapYear = true
        }
        else if (year % 100 == 0) {
            isLeapYear = false
        }
        else if (year % 4 == 0) {
            isLeapYear = true
        }
        else {
            isLeapYear = false
        }
        return isLeapYear
    }
}