package test.em.tickets.utils

import android.icu.text.SimpleDateFormat
import java.text.DecimalFormat
import java.util.Calendar

fun priceRefactor(price: String): String {
    return buildString {
        for (i in price.length - 1 downTo 0) {
            append(price[i])
            if ((price.length - i) % 3 == 0) {
                append(' ')
            }
        }
        reverse()
    }
}

fun getMonthByNumber(monthnum: Int): String {
    val c = Calendar.getInstance()
    val month_date = SimpleDateFormat("MMMM")
    c[Calendar.MONTH] = monthnum
    return month_date.format(c.time)
}

fun getFlightTime(depTime: String, arrTime: String): String {
    val dep = depTime.split(":")
    val arr = arrTime.split(":")

    val resMin = if (dep[0] < arr[0]) {
        arr[0].toInt()
    } else {
        arr[0].toInt() + 24
    } * 60 + arr[1].toInt() - dep[0].toInt() * 60 + dep[1].toInt()
    val formatter = DecimalFormat("#.#")
    return formatter.format(resMin.toFloat()/60)
}