package com.android.cattle360.ui.user.home.Cattle

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository
import retrofit2.http.Field

class CattleRepository(private var api : ApiService?= null)  : BaseRepository() {

    suspend fun LiveStockList(livestock_id: String) = safeApiCall {
        api?.livestockAPI(livestock_id)
     }

    suspend fun ImageLivestockList(livestock_id: String) = safeApiCall {
        api?.imagelivestockAPI(livestock_id)
    }

    suspend fun InsertBid(bid_amount: String,
                          user_id: String,
                          livestock_id: String) = safeApiCall {
        api?.insertBidAPI(bid_amount,user_id,livestock_id)
    }
}