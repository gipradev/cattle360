package com.android.cattle360.data.apiResponse

data class GetBiddingHistoryResponse(
    val `data`: List<DataBiddingHistory>,
    val status: String,
    val toal_bid_amount: String
)