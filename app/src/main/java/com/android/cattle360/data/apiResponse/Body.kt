package com.android.cattle360.data.apiResponse

data class Body(
    val authenticated: Boolean,
    val isPromoCodeValid: Boolean,
    val resultInfo: ResultInfo,
    val txnToken: String
)