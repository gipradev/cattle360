 package com.android.cattle360.data.demmyModels

import com.android.cattle360.R


data class AvailableCattleModel(
    var id: Int? = null,
    var title: String? = null,
    var subTitle: String? = null,
    var bidAmount: String? = null,
    var image: Int? = null,
)

object availableModelSupplier {
    val availableCattleItem = listOf(
        AvailableCattleModel(
            id = 0,
            title = "One",
            bidAmount = "14900.00",
            subTitle = "abcd",
            image = R.drawable.stock_one
        ),
        AvailableCattleModel(
            id = 0,
            title = "Two",
            bidAmount = "1900.00",
            subTitle = "abcd",
            image = R.drawable.stock_two
        ),
        AvailableCattleModel(
            id = 0,
            title = "Three",
            bidAmount = "175900.00",
            subTitle = "abcd",
            image = R.drawable.stock_three
        ),
        AvailableCattleModel(
            id = 0,
            title = "Three",
            bidAmount = "30000.00",
            subTitle = "abcd",
            image = R.drawable.stock_one
        ),
        AvailableCattleModel(
            id = 0,
            title = "Two",
            bidAmount = "35500.00",
            subTitle = "abcd",
            image = R.drawable.stock_two
        ),
        AvailableCattleModel(
            id = 0,
            title = "Three",
            bidAmount = "5900.00",
            subTitle = "abcd",
            image = R.drawable.stock_three
        ),
        AvailableCattleModel(
            id = 0,
            title = "Two",
            bidAmount = "1900.00",
            subTitle = "abcd",
            image = R.drawable.stock_two
        ),
        AvailableCattleModel(
            id = 0,
            title = "Two",
            bidAmount = "35500.00",
            subTitle = "abcd",
            image = R.drawable.stock_two
        ),

    )
}


