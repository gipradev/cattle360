package com.android.cattle360.data.apiResponse

data class GetLoginResponse(
    val message: String,
    val mobileno: String,
    val password: String,
    val status: String,
    val userid: String,
    val usertype: String
)