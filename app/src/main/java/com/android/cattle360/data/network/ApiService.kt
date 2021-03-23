package com.android.cattle360.data.network

import com.android.cattle360.data.apiResponse.*
import com.android.cattle360.data.util.ApiEndPoints.AREA_LIST
import com.android.cattle360.data.util.ApiEndPoints.CATEGORY
import com.android.cattle360.data.util.ApiEndPoints.CHANGE_PASSWORD
import com.android.cattle360.data.util.ApiEndPoints.DISTRICT_LIST
import com.android.cattle360.data.util.ApiEndPoints.EMPLOYEE_LOGIN
import com.android.cattle360.data.util.ApiEndPoints.EMP_LOGIN_CHECK
import com.android.cattle360.data.util.ApiEndPoints.INSERT_CATTLE
import com.android.cattle360.data.util.ApiEndPoints.LOGIN
import com.android.cattle360.data.util.ApiEndPoints.LOGIN_CHECK
import com.android.cattle360.data.util.ApiEndPoints.LOGOUT
import com.android.cattle360.data.util.ApiEndPoints.OTP_VERIFICATION_CHECKING
import com.android.cattle360.data.util.ApiEndPoints.REGISTRATION
import com.android.cattle360.data.util.ApiEndPoints.SEND_OTP
import com.android.cattle360.data.util.ApiEndPoints.SEND_USERNAME
import com.android.cattle360.data.util.ApiEndPoints.STATE_LIST
import com.android.cattle360.data.util.ApiEndPoints.VIEW_DEALER_MOBILE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST(SEND_OTP)
   suspend fun sendMobile(@Field("mobile_no") mobile : String) : GetOtpResponse

    @FormUrlEncoded
    @POST(SEND_USERNAME)
    suspend fun sendUsername(@Field("username") username : String) : GetUsernameResponse

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
    @POST(EMPLOYEE_LOGIN)
    suspend fun employeeloginAPI(@Field("username") username: String,
                         @Field("password") password: String) : GetEmployeeLoginResponse

    @FormUrlEncoded
    @POST(LOGIN_CHECK)
    suspend fun loginCheckAPI(@Field("mobile_no") mobile: String) : GetLoginCheckResponse

    @FormUrlEncoded
    @POST(EMP_LOGIN_CHECK)
    suspend fun employeeloginCheckAPI(@Field("username") username: String) : GetLoginCheckResponse

    @FormUrlEncoded
    @POST(CHANGE_PASSWORD)
    suspend fun changePasswordAPI(@Field("mobile_no") mobile: String,
                                  @Field("current_password") current_password: String,
                              @Field("user_password") new_password: String) : GetLogoutResponse

    @FormUrlEncoded
    @POST(LOGOUT)
    suspend fun loginOutAPI(@Field("mobile_no") mobile: String) : GetLogoutResponse

    @FormUrlEncoded
    @POST(INSERT_CATTLE)
    suspend fun insertCattleDetailsAPI(@Field("pincode")pincode_value: String,
                                       @Field("n_area")area_value: String,
                                       @Field("n_district") district_value: String,
                                       @Field("c_state")state_value: String,
                                       @Field("c_title")title_value: String,
                                       @Field("n_category")category_value: String,
                                       @Field("n_weight") weight_value: String,
                                       @Field("n_age") age_value: String,
                                       @Field("c_colour")color_value: String,
                                       @Field("n_bidding_price")biddingamount_value: String,
                                       @Field("n_customer_price")customerprice_value: String): GetLogoutResponse


    @GET(STATE_LIST)
    suspend fun stateAPI() : GetStateListResponse


    @FormUrlEncoded
    @POST(DISTRICT_LIST)
    suspend fun districtAPI(@Field("state_code") state_code: String) :GetDistrictListResponse

    @FormUrlEncoded
    @POST(AREA_LIST)
    suspend fun areaAPI(@Field("district_id") district_id: String) : GetAreaListResponse

    @FormUrlEncoded
    @POST(VIEW_DEALER_MOBILE)
    suspend fun verifyMobileAPI(@Field("mobile") mobile: String) : GetViewDealerResponse

    @GET(CATEGORY)
    suspend fun allCategoryAPI() : GetAllCategoryResponse

}