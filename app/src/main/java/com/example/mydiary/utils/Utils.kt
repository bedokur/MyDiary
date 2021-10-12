package com.example.mydiary.utils

import com.example.mydiary.models.TodoModel
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.Locale

class Utils {
    private val datesFormatter = SimpleDateFormat("dd-MM-yyyy ", Locale.getDefault())
    private val datesFormatterWithHours = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
    private val hoursFormatter = SimpleDateFormat("HH.mm", Locale.getDefault())

    fun roundModelsDate(model: TodoModel): TodoModel {
        val date_start = model.date_start
        val date_finish = model.date_finish
        val roundedStartTime = hoursFormatter.format(date_start).toBigDecimal().setScale(0, RoundingMode.DOWN)
        val roundedStopTime = hoursFormatter.format(date_finish).toBigDecimal().setScale(0, RoundingMode.UP)
        val formattedStartTime = "$roundedStartTime:00"
        val formattedStopTime = "$roundedStopTime:00"
        val newStartDate = datesFormatter.format(date_start).plus(formattedStartTime)
        val newStopDate = datesFormatter.format(date_finish).plus(formattedStopTime)

        return TodoModel(
            model.id,
            datesFormatterWithHours.parse(newStartDate)!!.time,
            datesFormatterWithHours.parse(newStopDate)!!.time,
            model.name,
            model.description
        )
    }
}