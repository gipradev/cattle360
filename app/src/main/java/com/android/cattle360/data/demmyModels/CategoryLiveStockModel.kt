package com.android.cattle360.data.demmyModels

import com.android.cattle360.R


data class CategoryLiveStockModel(
    var id: Int? = null,
    var title: String? = null,
    var subTitle: String? = null,
    var image: Int? = null,
)

object categoryStockSupplier {
    val stockItem = listOf(
        CategoryLiveStockModel(id = 0, title = "One", subTitle = "abcd", image = R.drawable.stock_one),
        CategoryLiveStockModel(id = 0, title = "Two", subTitle = "abcd", image = R.drawable.stock_two),
        CategoryLiveStockModel(id = 0, title = "Three", subTitle = "abcd", image = R.drawable.stock_three),
        CategoryLiveStockModel(id = 0, title = "Three",subTitle = "abcd", image =  R.drawable.stock_one),
        CategoryLiveStockModel(id = 0, title = "Two", subTitle = "abcd", image = R.drawable.stock_two),
        CategoryLiveStockModel(id = 0, title = "Three", subTitle = "abcd", image = R.drawable.stock_three),
    )
}


