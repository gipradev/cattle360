package com.android.cattle360.ui.appStart.otp

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository

class OtpRepository(private var api : ApiService?= null) : BaseRepository() {

    suspend fun otpVerification(otp: String) = safeApiCall {
        println("On ApiService ")
        api?.otpVerify(otp)
    }
}