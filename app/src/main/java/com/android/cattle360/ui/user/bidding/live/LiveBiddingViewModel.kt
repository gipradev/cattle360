package com.android.cattle360.ui.user.bidding.live

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetBiddingListResponse
import com.android.cattle360.data.demmyModels.LiveBidModel
import com.android.cattle360.data.demmyModels.liveBidSupplier
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.bidding.BiddingRepository
import kotlinx.coroutines.launch

class LiveBiddingViewModel(private val  repository: BiddingRepository) : BaseViewModel() {

    val liveBidResponse: LiveData<Resource<GetBiddingListResponse?>> = MutableLiveData()
//    fun getLiveBiddingList() {
//
//        liveBidResponse as MutableLiveData
//        liveBidResponse.value = liveBidSupplier.liveBidItem
//    }

fun getLiveBiddingList(user_id:String)  = viewModelScope.launch {
    liveBidResponse as MutableLiveData
    liveBidResponse.value = Resource.Loading
    //  liveStockResponse.value = stockSupplier.stockItem
    liveBidResponse.value = repository.biddingList(user_id)
}


}