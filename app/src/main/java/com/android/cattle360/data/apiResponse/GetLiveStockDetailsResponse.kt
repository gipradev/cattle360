package com.android.cattle360.data.apiResponse

data class GetLiveStockDetailsResponse(
    val `data`: List<DataLiveStockDetails>,
    val status: String
)