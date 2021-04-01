package com.android.cattle360.data.apiResponse

data class GetImageListResponse(
    val `data`: List<DataImageDefaultView>,
    val status: String
)