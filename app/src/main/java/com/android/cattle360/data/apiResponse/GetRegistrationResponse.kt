package com.android.cattle360.data.apiResponse

data class GetRegistrationResponse(
    val Name: String,
    val Last_Name: String,
    val Userid: Int,
    val email: String,
    val message: String,
    val mobile: String,
    val password: String,
    val status: String,
    val usertype: String
)