package com.example.automotomaintenance.util

import java.text.SimpleDateFormat
import java.util.*

private const val SIMPLE_DATE_FORMAT = "dd.MM.yyyy"

fun getData(dataService: Date): String {

    val date = SimpleDateFormat(SIMPLE_DATE_FORMAT)
    return date.format(dataService).toString()
}
