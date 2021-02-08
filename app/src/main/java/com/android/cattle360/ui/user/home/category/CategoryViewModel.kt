package com.android.cattle360.ui.user.home.category


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.cattle360.data.demmyModels.CategoryModel
import com.android.cattle360.data.demmyModels.categorySupplier
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.home.banner.BannerRepository

class CategoryViewModel(private val  repository: CategoryRepository) : BaseViewModel() {

    val categoryResponse: LiveData<List<CategoryModel>> = MutableLiveData()


    fun getCategoryList() {
        categoryResponse as MutableLiveData
        categoryResponse.value = categorySupplier.categoryItem
    }
}