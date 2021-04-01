package com.android.cattle360.ui.user.home.category


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetAllCategoryResponse
import com.android.cattle360.data.apiResponse.GetLiveStockDetails
import com.android.cattle360.data.demmyModels.CategoryModel
import com.android.cattle360.data.demmyModels.categorySupplier
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.home.banner.BannerRepository
import kotlinx.coroutines.launch

class CategoryViewModel(private val  repository: CategoryRepository) : BaseViewModel() {

    val categoryResponse: LiveData<Resource<GetAllCategoryResponse?>> = MutableLiveData()


    fun getCategoryList() = viewModelScope.launch {

        categoryResponse as MutableLiveData
        categoryResponse.value = Resource.Loading
        //categoryResponse.value = categorySupplier.categoryItem
        categoryResponse.value = repository.homeCategoryList()
        println("category response"+categoryResponse.value)
    }




}