package com.android.cattle360.data.apiResponse

data class GetCategoryListResponse(
    val `data`: List<DataCategoryList>,
    val status: String,
    val category: String
    )