package com.example.innovecstask.common.utils

import android.util.Log
import java.util.*

class Time {
    companion object {
        fun getCurrentDayOfWeek(): Int {
            val calendar: Calendar = Calendar.getInstance()
            return calendar.get(Calendar.DAY_OF_WEEK) - 1
        }
    }
}