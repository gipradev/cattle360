package com.android.cattle360.ui.user.history.biddingHistoryList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.cattle360.data.demmyModels.LiveBidModel
import com.android.cattle360.data.demmyModels.liveBidSupplier
import com.android.cattle360.ui.base.BaseViewModel

class BiddingHistoryListViewModel : BaseViewModel() {
    val liveBidResponse: LiveData<List<LiveBidModel>> = MutableLiveData()


    fun getBiddingHistory() {
        liveBidResponse as MutableLiveData
        liveBidResponse.value = liveBidSupplier.liveBidItem
    }
}