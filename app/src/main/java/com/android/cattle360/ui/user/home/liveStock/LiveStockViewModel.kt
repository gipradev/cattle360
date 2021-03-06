package com.android.cattle360.ui.user.home.liveStock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.cattle360.data.demmyModels.LiveStockModel
import com.android.cattle360.data.demmyModels.stockSupplier
import com.android.cattle360.ui.base.BaseViewModel

class LiveStockViewModel : BaseViewModel() {

        val liveStockResponse: LiveData<List<LiveStockModel>> = MutableLiveData()


    fun getLiveStockList() {
        liveStockResponse as MutableLiveData
        liveStockResponse.value = stockSupplier.stockItem
    }
}