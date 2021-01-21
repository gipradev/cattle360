package com.android.cattle360.data.demmyModels

import com.android.cattle360.R


data class CategoryListModel(
    val categoryName: String,
    val categoryImage: Int,
    val categoryItem: List<CategoryLiveStockModel>,
    val status: String
)

object categoryModelSupplier {
    val categoryList = CategoryListModel(
        categoryName = "Buffalo",
        categoryImage = R.drawable.image_one,
        categoryItem = categoryStockSupplier.stockItem,
        status = "Success"
    )

}