package com.msd.newsfeedapp.ui.util


import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

object SimpleDateFormatter {
    fun formatDateTime(isoDate: String): String {
        return try {
            val instant = Instant.parse(isoDate)
            val local = instant.atZone(ZoneId.systemDefault())

            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .format(local)
        } catch (e: Exception) {
            isoDate
        }
    }
}