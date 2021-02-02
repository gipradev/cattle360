package com.android.cattle360.data.apiResponse

data class GetOtpResponse(
    val OTP: String,
    val OTP_expiry: String,
    val usertype: String,
    val message: String,
    val status: String
)