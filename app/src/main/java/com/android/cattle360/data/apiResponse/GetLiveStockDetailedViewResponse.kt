package com.android.cattle360.data.apiResponse

data class GetLiveStockDetailedViewResponse(
    val auction_date_time: String,
    val area: String,
    val c_colour: String,
    val c_description: String,
    val c_image: String,
    val c_offspring: String,
    val c_title: String,
    val c_vaccination: String,
    val category: String,
    val d_added_date: String,
    val district: String,
    val n_age: String,
    val n_customer_price: String,
    val n_livestock_id: String,
    val n_min_bidding_price: String,
    val n_weight: String,
    val state: String,
    val status: String
)