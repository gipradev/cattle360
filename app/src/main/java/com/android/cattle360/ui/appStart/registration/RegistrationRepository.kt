package com.android.cattle360.ui.appStart.registration

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository

class RegistrationRepository(private var api : ApiService?= null) : BaseRepository() {
    suspend fun onRegistration(Name: String,
                               Last_Name: String,
                               mobile: String,
                               email: String,
                               password: String,
                               confirm_password: String) = safeApiCall{
        println("On ApiService ")
        api?.registration(Name,Last_Name,mobile,email,password,confirm_password)
}
}