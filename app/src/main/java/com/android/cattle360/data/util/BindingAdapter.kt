package com.android.cattle360.data.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation


/**
 * File for declaring custom binding methods
 */



/**
 * Image binding
 */


@BindingAdapter("coilLoader")
fun coilLoader(view: ImageView, image: Int) {
    view.load(image) {
        transformations(CircleCropTransformation())
    }
}

@BindingAdapter("coilFullLoader")
fun coilFullLoader(view: ImageView, image: Int) {
    view.load(image) {

    }
}

@BindingAdapter("coilCurvedLoader")
fun coilCurvedLoader(view: ImageView, image: Int) {
    view.load(image) {
        transformations(RoundedCornersTransformation(10f,10f,0f,0f))
    }
}


@BindingAdapter("coilFullCurvedLoader")
fun coilFullCurvedLoader(view: ImageView, image: Int) {
    view.load(image) {
        transformations(RoundedCornersTransformation(10f))
    }
}




