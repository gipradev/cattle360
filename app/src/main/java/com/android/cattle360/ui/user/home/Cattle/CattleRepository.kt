package com.android.cattle360.ui.user.home.Cattle

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository

class CattleRepository(private var api : ApiService?= null)  : BaseRepository() {

    suspend fun LiveStockList(livestock_id: String) = safeApiCall {
        api?.livestockAPI(livestock_id)
     }

    suspend fun ImageLivestockList(livestock_id: String) = safeApiCall {
        api?.imagelivestockAPI(livestock_id)
    }
}