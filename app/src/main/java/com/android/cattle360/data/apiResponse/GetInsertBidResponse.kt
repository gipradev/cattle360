package com.android.cattle360.data.apiResponse

data class GetInsertBidResponse(
    val d_date_time: String,
    val message: String,
    val n_bid_amount: String,
    val n_id: String,
    val n_livestock_id: String,
    val status: String,
    val transaction_no: Int
)