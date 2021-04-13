package com.android.cattle360.ui.user.home.category.categoryList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetAllCategoryResponse
import com.android.cattle360.data.apiResponse.GetCategoryListResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.home.category.CategoryRepository

import kotlinx.coroutines.launch

class CategoryListViewModel (private val  repository: CategoryRepository): BaseViewModel() {


    val categoryResponse:  LiveData<Resource<GetCategoryListResponse?>> = MutableLiveData()

    fun getCategoryList(category_id: String) =viewModelScope.launch{

        categoryResponse as MutableLiveData
    //    categoryResponse.value = categoryModelSupplier.categoryList
        categoryResponse.value = repository.CategoryList(category_id)
        println(categoryResponse.value )
    }

}