package com.android.cattle360.data.demmyModels

import com.android.cattle360.R


data class CategoryModel(
    var id: Int? = null,
    var name: String? = null,
    var image: Int? = null,
)

object categorySupplier {
    val categoryItem = listOf(
        CategoryModel(id = 0 , name = "One" , R.drawable.cw1),
        CategoryModel(id = 0 , name = "Two" , R.drawable.cw3),
        CategoryModel(id = 0 , name = "Three" , R.drawable.cw4),
        CategoryModel(id = 0 , name = "Three" , R.drawable.cw3),
        CategoryModel(id = 0 , name = "Two" , R.drawable.cw1),
        CategoryModel(id = 0 , name = "Three" , R.drawable.cw4),
    )
}


