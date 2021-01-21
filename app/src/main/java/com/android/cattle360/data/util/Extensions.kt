package com.android.cattle360.data.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.view.Window


fun Int.toDp() : Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx() : Int = (this * Resources.getSystem().displayMetrics.density).toInt()


//fun statuscolor() {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//        val window: Window = getWindow()
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.setStatusBarColor(
//            Color.parseColor(
//                getPreferences().getString(
//                    Constant.SECONDARY_COLOR,
//                    Constant.SECONDARY_COLOR
//                )
//            )
//        )
//    }
//}

fun Context.changeStatusBarColor(window: Window, red: Int, green: Int, blue: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = Color.rgb(red,green,blue)
    }
}