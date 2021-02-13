package com.android.cattle360.ui.user.profile

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository

class ProfileRepository(private var api : ApiService?= null) : BaseRepository() {
    suspend fun onLogout(mobile: String) = safeApiCall{
        api?.loginOutAPI(mobile)
}
}