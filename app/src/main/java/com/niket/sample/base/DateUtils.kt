package com.niket.sample.base

import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT_D_MMM_YYYY_TIME = "d MMM, yyyy | hh:mm a"
const val COMMON_UI_FORMAT = DATE_FORMAT_D_MMM_YYYY_TIME

fun getDateFormatter(format: String = COMMON_UI_FORMAT) =
    SimpleDateFormat(format, Locale.ENGLISH)

fun Date.formatAsString(format: String = COMMON_UI_FORMAT) = getDateFormatter(format).format(this)

fun String.parseAsDate(format: String = COMMON_UI_FORMAT) =
    getDateFormatter(format).parse(this)