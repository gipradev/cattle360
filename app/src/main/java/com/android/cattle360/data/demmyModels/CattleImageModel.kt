package com.android.cattle360.data.demmyModels

import com.android.cattle360.R

data class CattleImageModel(var image: Int)


object cattleImageSuppiler {
    val cattleImageItem = listOf(
        CattleImageModel(image = R.drawable.image_one),
        CattleImageModel(image = R.drawable.image_two),
        CattleImageModel(image = R.drawable.image_three),
        CattleImageModel(image = R.drawable.image_four),
        CattleImageModel(image = R.drawable.image_five),
        CattleImageModel(image = R.drawable.image_one),
        CattleImageModel(image = R.drawable.image_two),
        CattleImageModel(image = R.drawable.image_three),
        CattleImageModel(image = R.drawable.image_four),
        CattleImageModel(image = R.drawable.image_five),
    )


}
