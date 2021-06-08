package com.android.cattle360.ui.user.bidding.live

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.cattle360.data.demmyModels.LiveBidModel
import com.android.cattle360.data.demmyModels.liveBidSupplier
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.bidding.BiddingRepository

class LiveBiddingViewModel(private val  repository: BiddingRepository) : BaseViewModel() {

    val liveBidResponse: LiveData<List<LiveBidModel>> = MutableLiveData()

    fun getLiveBiddingList() {

        liveBidResponse as MutableLiveData
        liveBidResponse.value = liveBidSupplier.liveBidItem
    }


}