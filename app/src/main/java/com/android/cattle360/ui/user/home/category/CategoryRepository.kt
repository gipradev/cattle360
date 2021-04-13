package com.android.cattle360.ui.user.home.category

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository

class CategoryRepository(private var api : ApiService?= null) : BaseRepository() {

    suspend fun homeCategoryList() = safeApiCall {
        api?.homeCategoryAPI()
    }

    suspend fun CategoryList(category_id: String) = safeApiCall {
        api?.categoryAPI(category_id)
    }

}