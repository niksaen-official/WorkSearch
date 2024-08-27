package com.niksaen.worksearch.utils

class MonthMap {
    companion object {
        private val monthMap = hashMapOf(
            Pair(1, "января"),
            Pair(2, "февраля"),
            Pair(3, "марта"),
            Pair(4, "апреля"),
            Pair(5, "мая"),
            Pair(6, "июня"),
            Pair(7, "июля"),
            Pair(8, "августа"),
            Pair(9, "сенятбря"),
            Pair(10, "октября"),
            Pair(11, "ноября"),
            Pair(12, "декабря"),
        )
        /**
         * Use this method for month name of month number
         * */
        fun getMonth(month: Int) = if (month in (1..12)) monthMap[month] else ""
    }
}