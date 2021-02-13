package com.android.cattle360.ui.user.profile.changePassword

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository

class ChangPasswordRepository(private var api : ApiService?= null) : BaseRepository() {

    suspend fun changePassword(mobile: String,
                               current_password: String,
                               new_password: String) = safeApiCall {
        api?.changePasswordAPI(mobile,current_password, new_password)
    }
}