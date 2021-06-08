package com.android.cattle360.ui.user.home.Cattle.cattleImageSlider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetLivestockImageListResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.home.Cattle.CattleRepository
import com.android.cattle360.ui.user.home.category.CategoryRepository
import kotlinx.coroutines.launch

class ImageSliderViewModel (private val  repository: CattleRepository): BaseViewModel() {

    val cattleImageResponse: LiveData<Resource<GetLivestockImageListResponse?>> = MutableLiveData()

    fun getCattleImageData(livestock_id:String) = viewModelScope.launch {
        cattleImageResponse as MutableLiveData
        cattleImageResponse.value = Resource.Loading
        //cattleResponse.value = cattleModelSupplier.cattle
        cattleImageResponse.value = repository.ImageLivestockList(livestock_id)
    }

}