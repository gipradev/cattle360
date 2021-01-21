package com.android.cattle360.ui.user.bidding.live

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.cattle360.data.demmyModels.LiveBidModel
import com.android.cattle360.data.demmyModels.liveBidSupplier

class LiveBiddingViewModel : ViewModel() {

    val liveBidResponse: LiveData<List<LiveBidModel>> = MutableLiveData()

    fun getLiveBiddingList() {

        liveBidResponse as MutableLiveData
        liveBidResponse.value = liveBidSupplier.liveBidItem
    }


}