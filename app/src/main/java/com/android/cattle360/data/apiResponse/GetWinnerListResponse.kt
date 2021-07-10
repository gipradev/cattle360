package com.android.cattle360.data.apiResponse

data class GetWinnerListResponse(
    val `data`: List<DataWinnerList>,
    val status: String,
    val toal_bid_amount: Int
)