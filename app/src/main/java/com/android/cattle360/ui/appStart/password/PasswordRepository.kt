package com.android.cattle360.ui.appStart.password

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository
import retrofit2.http.Field

class PasswordRepository (private var api : ApiService?= null): BaseRepository() {

    suspend fun userLogin(mobile: String,password: String) = safeApiCall {
        println("On ApiService ")
        api?.loginAPI(mobile,password)
    }

    suspend fun employeeLoginBy(username: String,password: String) = safeApiCall {
        println("On ApiService ")
        api?.employeeloginAPI(username,password)
    }
}