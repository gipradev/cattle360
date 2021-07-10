package com.android.cattle360.ui.user.bidding

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository

class BiddingRepository (private var api : ApiService?= null): BaseRepository() {

    suspend fun biddingList(user_id:String) = safeApiCall {
        api?.biddingListAPI(user_id)
    }
    suspend fun biddingHistoryList(user_id:String) = safeApiCall {
        api?.biddingHistoryAPI(user_id)
    }

    suspend fun winnerList(user_id:String) = safeApiCall {
        api?.winnerListAPI(user_id)
    }
}