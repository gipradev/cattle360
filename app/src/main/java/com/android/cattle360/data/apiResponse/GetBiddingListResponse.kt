package com.android.cattle360.data.apiResponse

data class GetBiddingListResponse(
    val `data`: List<DataBiddingList>,
    val status: String,
    val toal_bid_amount: Int
)