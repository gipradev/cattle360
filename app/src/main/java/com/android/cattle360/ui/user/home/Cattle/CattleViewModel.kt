package com.android.cattle360.ui.user.home.Cattle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetAllLivestockResponse
import com.android.cattle360.data.apiResponse.GetCategoryListResponse
import com.android.cattle360.data.apiResponse.GetLiveStockDetailedViewResponse
import com.android.cattle360.data.apiResponse.GetLivestockImageListResponse
import com.android.cattle360.data.demmyModels.CattleModel
import com.android.cattle360.data.demmyModels.cattleModelSupplier
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.home.category.CategoryRepository
import kotlinx.coroutines.launch

class CattleViewModel(private val  repository: CattleRepository) : BaseViewModel() {

    val cattleResponse: LiveData<Resource<GetLiveStockDetailedViewResponse?>> = MutableLiveData()

    fun getCattleData(livestock_id:String) = viewModelScope.launch {
        cattleResponse as MutableLiveData
        cattleResponse.value = Resource.Loading
        //cattleResponse.value = cattleModelSupplier.cattle
        cattleResponse.value = repository.LiveStockList(livestock_id)
    }





}