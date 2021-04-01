package com.android.cattle360.data.apiResponse

data class GetLiveStockDetails(
    val `data`: List<DataLiveStockDetails>,
    val status: String
)