package com.android.cattle360.data.network

import com.android.cattle360.data.apiResponse.*
import com.android.cattle360.data.util.ApiEndPoints.CHANGE_PASSWORD
import com.android.cattle360.data.util.ApiEndPoints.LOGIN
import com.android.cattle360.data.util.ApiEndPoints.LOGIN_CHECK
import com.android.cattle360.data.util.ApiEndPoints.LOGOUT
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
        @Field("C_PASSWORD2") confirm_password: String): GetRegistrationResponse

    @FormUrlEncoded
    @POST(LOGIN)
    suspend fun loginAPI(@Field("mobile_no") mobile: String,
                      @Field("password") password: String) : GetLoginResponse

    @FormUrlEncoded
    @POST(LOGIN_CHECK)
    suspend fun loginCheckAPI(@Field("mobile_no") mobile: String) : GetLoginCheckResponse

    @FormUrlEncoded
    @POST(CHANGE_PASSWORD)
    suspend fun changePasswordAPI(@Field("mobile_no") mobile: String,
                                  @Field("current_password") current_password: String,
                              @Field("user_password") new_password: String) : GetLogoutResponse

    @FormUrlEncoded
    @POST(LOGOUT)
    suspend fun loginOutAPI(@Field("mobile_no") mobile: String) : GetLogoutResponse

}