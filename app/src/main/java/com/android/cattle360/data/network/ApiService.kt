package com.android.cattle360.data.network

import com.android.cattle360.data.apiResponse.GetLoginResponse
import com.android.cattle360.data.apiResponse.GetOtpResponse
import com.android.cattle360.data.apiResponse.GetRegistrationResponse
import com.android.cattle360.data.apiResponse.GetResponse
import com.android.cattle360.data.util.ApiEndPoints.LOGIN
import com.android.cattle360.data.util.ApiEndPoints.OTP_VERIFICATION_CHECKING
import com.android.cattle360.data.util.ApiEndPoints.REGISTRATION
import com.android.cattle360.data.util.ApiEndPoints.SEND_OTP
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST(SEND_OTP)
   suspend fun sendMobile(@Field("mobile_no") mobile : String) : GetOtpResponse

    @FormUrlEncoded
    @POST(OTP_VERIFICATION_CHECKING)
    suspend fun otpVerify(@Field("c_otp") otp : String) : GetResponse

    @FormUrlEncoded
    @POST(REGISTRATION)
    suspend fun registrationAPI(
        @Field("c_firstname")  name: String,
        @Field("c_lastname") lastname: String,
        @Field("mobile_no") moblile: String,
        @Field("email") email: String,
        @Field("C_PASSWORD") password: String,
        @Field("C_PASSWORD2") confirm_password: String
    ): GetRegistrationResponse

    @FormUrlEncoded
    @POST(LOGIN)
    suspend fun loginAPI(@Field("mobile_no") mobile: String,
                      @Field("password") password: String) : GetLoginResponse
}