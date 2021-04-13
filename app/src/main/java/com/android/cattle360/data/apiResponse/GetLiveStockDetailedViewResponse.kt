package com.android.cattle360.data.apiResponse

data class GetLiveStockDetailedViewResponse(
    val n_livestock_id: String,
    val c_title: String,
    val c_description: String,
    val n_min_bidding_price: String,
    val c_image: String,
    val n_age: String,
    val n_weight: String,
    val c_vaccination: String,
    val c_offspring: String,
    val c_colour: String,
    val n_customer_price: String,
    val d_added_date: String,
    val category: String,
    val status: String
)