package com.android.cattle360.ui.user.history.biddingHistoryList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.cattle360.data.demmyModels.LiveBidModel
import com.android.cattle360.data.demmyModels.liveBidSupplier
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.bidding.BiddingRepository

class BiddingHistoryListViewModel(private val  repository: BiddingRepository) : BaseViewModel() {
    val liveBidResponse: LiveData<List<LiveBidModel>> = MutableLiveData()


    fun getBiddingHistory() {
        liveBidResponse as MutableLiveData
        liveBidResponse.value = liveBidSupplier.liveBidItem
    }
}