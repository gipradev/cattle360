package com.android.cattle360.data.apiResponse

data class GetImageUploadResponse(
    val c_model: String,
    val c_type: String,
    val image: String,
    val message: String,
    val status: String
)