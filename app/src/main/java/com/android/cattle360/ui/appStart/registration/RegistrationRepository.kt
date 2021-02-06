package com.android.cattle360.ui.appStart.registration

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository

class RegistrationRepository(private var api : ApiService?= null) : BaseRepository() {
    suspend fun onRegistration(name: String,
                               lastname: String,
                               mobile: String,
                               email: String,
                               password: String,
                               confirm_password: String) = safeApiCall{
        api?.registrationAPI(name,lastname,mobile,email,password,confirm_password)
}
}