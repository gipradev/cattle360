package com.android.cattle360.data.demmyModels

import com.android.cattle360.R


data class BannerModel(
    var id: Int? = null,
    var name: String? = null,
    var image: Int? = null,
)

object bannerSupplier {
    val bannerItem = listOf(
        BannerModel(id = 0 , name = "" , R.drawable.bbb),
        BannerModel(id = 0 , name = "" , R.drawable.bbb),
        BannerModel(id = 0 , name = "" , R.drawable.bbb),
    )
}


