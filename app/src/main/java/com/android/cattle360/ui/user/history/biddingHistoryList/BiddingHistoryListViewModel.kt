package com.android.cattle360.ui.user.history.biddingHistoryList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetBiddingHistoryResponse
import com.android.cattle360.data.demmyModels.LiveBidModel
import com.android.cattle360.data.demmyModels.liveBidSupplier
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.bidding.BiddingRepository
import kotlinx.coroutines.launch

class BiddingHistoryListViewModel(private val  repository: BiddingRepository) : BaseViewModel() {
    val liveBidHistoryResponse: LiveData<Resource<GetBiddingHistoryResponse?>> = MutableLiveData()


//    fun getBiddingHistory() {
//        liveBidResponse as MutableLiveData
//        liveBidResponse.value = liveBidSupplier.liveBidItem
//    }

fun getBiddingHistory(user_id:String)  = viewModelScope.launch {
    liveBidHistoryResponse as MutableLiveData
    liveBidHistoryResponse.value = Resource.Loading
    //  liveStockResponse.value = stockSupplier.stockItem
    liveBidHistoryResponse.value = repository.biddingHistoryList(user_id)
}

}