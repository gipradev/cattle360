package com.android.cattle360.ui.user.history.totalFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetBiddingHistoryResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.bidding.BiddingRepository
import kotlinx.coroutines.launch

class TotalAmountViewModel(private val  repository: BiddingRepository) : BaseViewModel() {


    val liveBidHistorytotalResponse: LiveData<Resource<GetBiddingHistoryResponse?>> = MutableLiveData()


    //    fun getBiddingHistory() {
//        liveBidResponse as MutableLiveData
//        liveBidResponse.value = liveBidSupplier.liveBidItem
//    }
    fun getBiddingHistorytotal(user_id:String)  = viewModelScope.launch {
        liveBidHistorytotalResponse as MutableLiveData
        liveBidHistorytotalResponse.value = Resource.Loading
        //  liveStockResponse.value = stockSupplier.stockItem
        liveBidHistorytotalResponse.value = repository.biddingHistoryList(user_id)
    }

}