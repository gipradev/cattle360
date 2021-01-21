package com.android.cattle360.data.demmyModels

import com.android.cattle360.R

data class CartListModel(
    var image: Int ,
    var modelName: String,
    var modelId: Int?= null
)


object cartListSuppiler {
    val cartItem = listOf(
        CartListModel(image = R.drawable.image_one  ,modelName = "Item 1",modelId = null),
        CartListModel(image = R.drawable.image_two  ,modelName = "Item 2",modelId = null),
        CartListModel(image = R.drawable.image_three,modelName = "Item 3",modelId = null),
        CartListModel(image = R.drawable.image_four ,modelName = "Item 4",modelId = null),
        CartListModel(image = R.drawable.image_five ,modelName = "Item 5",modelId = null),
        CartListModel(image = R.drawable.image_one  ,modelName = "Item 6",modelId = null),
        CartListModel(image = R.drawable.image_two  ,modelName = "Item 7",modelId = null),
        CartListModel(image = R.drawable.image_three,modelName = "Item 8",modelId = null),
        CartListModel(image = R.drawable.image_four ,modelName = "Item 9",modelId = null),
        CartListModel(image = R.drawable.image_five ,modelName = "Item 0",modelId = null),
    )


}
