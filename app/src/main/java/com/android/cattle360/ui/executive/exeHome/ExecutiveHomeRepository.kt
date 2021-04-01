package com.android.cattle360.ui.executive.exeHome

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository

class ExecutiveHomeRepository (private var api : ApiService?= null): BaseRepository() {


    suspend fun cattleLiveStock(userid: String) = safeApiCall {
        api?.listLiveStockAPI(userid)
    }

}