package com.niket.sample.feature.helper

import android.view.View
import com.niket.sample.feature.model.PostResponseItem

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.GONE
}