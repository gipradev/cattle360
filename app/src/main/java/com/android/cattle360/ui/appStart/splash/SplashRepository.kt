package com.android.cattle360.ui.appStart.splash

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository

class SplashRepository(private val api: ApiService ?= null) : BaseRepository() {
    suspend fun userLoginCheck(mobile: String) = safeApiCall {
        println("On ApiService ")
        api?.loginCheckAPI(mobile)
    }
    suspend fun empLoginCheck(username: String) = safeApiCall {
        println("On ApiService ")
        api?.employeeloginCheckAPI(username)
    }
}