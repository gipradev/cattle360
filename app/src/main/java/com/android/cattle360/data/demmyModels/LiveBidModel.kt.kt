package com.android.cattle360.data.demmyModels

import com.android.cattle360.R


data class LiveBidModel(
    var id: Int? = null,
    var title: String? = null,
    var subTitle: String? = null,
    var bidAmount: String? = null,
    var image: Int? = null,
)

object liveBidSupplier {
    val liveBidItem = listOf(
        LiveBidModel(
            id = 0,
            title = "One",
            bidAmount = "14900.00",
            subTitle = "abcd",
            image = R.drawable.stock_one
        ),
        LiveBidModel(
            id = 0,
            title = "Two",
            bidAmount = "1900.00",
            subTitle = "abcd",
            image = R.drawable.stock_two
        ),
        LiveBidModel(
            id = 0,
            title = "Three",
            bidAmount = "175900.00",
            subTitle = "abcd",
            image = R.drawable.stock_three
        ),
        LiveBidModel(
            id = 0,
            title = "Three",
            bidAmount = "30000.00",
            subTitle = "abcd",
            image = R.drawable.stock_one
        ),
        LiveBidModel(
            id = 0,
            title = "Two",
            bidAmount = "35500.00",
            subTitle = "abcd",
            image = R.drawable.stock_two
        ),
        LiveBidModel(
            id = 0,
            title = "Three",
            bidAmount = "5900.00",
            subTitle = "abcd",
            image = R.drawable.stock_three
        ),
    )
}


