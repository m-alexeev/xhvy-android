package com.example.xhvy.domain.utils

import java.util.Date

fun calcTimeDifference(time1: Date, time2: Date): Long {
    return time1.toInstant().epochSecond - time2.toInstant().epochSecond
}