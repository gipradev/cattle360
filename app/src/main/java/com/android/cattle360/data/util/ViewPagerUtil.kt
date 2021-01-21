package com.android.cattle360.data.util


import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.styleViewPager(viewPager2: ViewPager2) {
    viewPager2.clipToPadding = true
    viewPager2.clipChildren = false
    viewPager2.offscreenPageLimit = 3
    viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
}