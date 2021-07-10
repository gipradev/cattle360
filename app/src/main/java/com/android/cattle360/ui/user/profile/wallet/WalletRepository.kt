package com.android.cattle360.ui.user.profile.wallet

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository
import okhttp3.RequestBody

class WalletRepository(private var api : ApiService?= null) : BaseRepository() {

    suspend fun onGetToken(code: String,
                           MID: String,
                           ORDER_ID: String,
                           AMOUNT: String
    ) = safeApiCall{
        api?.getTokenAPI(code,MID,ORDER_ID,AMOUNT)
    }


}