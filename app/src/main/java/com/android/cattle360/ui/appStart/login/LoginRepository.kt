package com.android.cattle360.ui.appStart.login

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository

class LoginRepository(private var api : ApiService ?= null) : BaseRepository() {

   suspend fun sendMobileToService(mobile: String) = safeApiCall{
       println("On ApiService ")
        api?.sendMobile(mobile)
    }
}