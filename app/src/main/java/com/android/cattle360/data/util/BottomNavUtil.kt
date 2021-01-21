package com.android.cattle360.data.util

import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

fun View.visibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}