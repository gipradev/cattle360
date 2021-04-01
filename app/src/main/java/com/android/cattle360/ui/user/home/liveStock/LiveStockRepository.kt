package com.android.cattle360.ui.user.home.liveStock

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository

class LiveStockRepository(private var api : ApiService?= null) : BaseRepository() {

    suspend fun homeLivestockList() = safeApiCall {
        api?.homeLivestockAPI()
    }

}