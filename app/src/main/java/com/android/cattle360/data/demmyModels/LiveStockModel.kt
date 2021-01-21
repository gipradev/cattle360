package com.android.cattle360.data.demmyModels

import com.android.cattle360.R


data class LiveStockModel(
    var id: Int? = null,
    var title: String? = null,
    var subTitle: String? = null,
    var image: Int? = null,
)

object stockSupplier {
    val stockItem = listOf(
        LiveStockModel(id = 0, title = "One", subTitle = "abcd", image = R.drawable.stock_one),
        LiveStockModel(id = 0, title = "Two", subTitle = "abcd", image = R.drawable.stock_two),
        LiveStockModel(id = 0, title = "Three", subTitle = "abcd", image = R.drawable.stock_three),
        LiveStockModel(id = 0, title = "Three",subTitle = "abcd", image =  R.drawable.stock_one),
        LiveStockModel(id = 0, title = "Two", subTitle = "abcd", image = R.drawable.stock_two),
        LiveStockModel(id = 0, title = "Three", subTitle = "abcd", image = R.drawable.stock_three),
    )
}


