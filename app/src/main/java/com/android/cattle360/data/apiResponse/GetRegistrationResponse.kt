package com.android.cattle360.data.apiResponse

data class GetRegistrationResponse(
    val status: String,
    val message: String,
    val userid: Int,
    val name: String,
    val lastname: String,
    val mobile: String,
    val email: String,
    val password: String,
    val usertype: String

)