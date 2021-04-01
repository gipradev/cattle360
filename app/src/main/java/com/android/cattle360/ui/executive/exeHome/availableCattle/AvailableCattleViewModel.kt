package com.android.cattle360.ui.executive.exeHome.availableCattle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetLiveStockDetails
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.executive.exeHome.ExecutiveHomeRepository
import kotlinx.coroutines.launch

class AvailableCattleViewModel(private val  repository: ExecutiveHomeRepository): BaseViewModel() {

    val cattleResponse: LiveData<Resource<GetLiveStockDetails?>> = MutableLiveData()

    fun getCattleList(userid: String) = viewModelScope.launch {
        cattleResponse as MutableLiveData
        cattleResponse.value = Resource.Loading
     // cattleResponse.value = availableModelSupplier.availableCattleItem
        cattleResponse.value = repository.cattleLiveStock(userid)

    }


}