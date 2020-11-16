package com.example.itunesmasterdetail.utils

import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class TimeUtilities {


    companion object {

        private val dateFormat: String = "d MMM yyyy"

        @JvmStatic
        fun isCurrentDate(localDateTime: LocalDateTime): Boolean {
            return getLocalDateTime() == localDateTime
        }

        @JvmStatic
        fun getLocalDateTime(lastSeenDateValue: String) : LocalDateTime {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(TextUtilities.getLongFromString(lastSeenDateValue)), ZoneId.systemDefault())
        }

        @JvmStatic
        fun getLocalDateTimeString(lastSeenDateValue: String) : String {
            val localDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(TextUtilities.getLongFromString(lastSeenDateValue)), ZoneId.systemDefault())
            return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE).toString()
        }

        @JvmStatic
        fun getCurrentTimeStamp(): Long {
            return getTimeZonedAlignedCalendar().timeInMillis
        }

        @JvmStatic
        fun getLocalDateTime(): LocalDateTime {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(getCurrentTimeStamp()), ZoneId.systemDefault())

        }

        fun getLocalDateTimeString(): String {
            val localDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(getCurrentTimeStamp()), ZoneId.systemDefault())
            return localDate.format(DateTimeFormatter.ofPattern(dateFormat)).toString()
        }

        @JvmStatic
        fun getTimeZonedAlignedCalendar(): Calendar {
            return Calendar.getInstance(TimeZone.getDefault())
        }

    }

}