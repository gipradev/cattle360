package com.android.cattle360.ui.user.home.liveStock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetAllLivestockResponse
import com.android.cattle360.data.demmyModels.LiveStockModel
import com.android.cattle360.data.demmyModels.stockSupplier
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.home.cattleCart.CattleCartRepository
import kotlinx.coroutines.launch

class LiveStockViewModel(private val  repository: LiveStockRepository) : BaseViewModel() {

        val liveStockResponse: LiveData<Resource<GetAllLivestockResponse?>> = MutableLiveData()


    fun getLiveStockList()  = viewModelScope.launch {
        liveStockResponse as MutableLiveData
        liveStockResponse.value = Resource.Loading
      //  liveStockResponse.value = stockSupplier.stockItem
        liveStockResponse.value = repository.homeLivestockList()
    }
}