package com.android.cattle360.ui.user.home.category.categoryList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.cattle360.data.demmyModels.CategoryListModel
import com.android.cattle360.data.demmyModels.categoryModelSupplier
import com.android.cattle360.ui.base.BaseViewModel

class CategoryListViewModel : BaseViewModel() {


    val categoryResponse: LiveData<CategoryListModel> = MutableLiveData()

    fun getCategoryList() {
        categoryResponse as MutableLiveData
        categoryResponse.value = categoryModelSupplier.categoryList
    }

}