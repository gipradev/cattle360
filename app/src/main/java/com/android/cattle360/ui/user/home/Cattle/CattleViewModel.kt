package com.android.cattle360.ui.user.home.Cattle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.cattle360.data.demmyModels.CattleModel
import com.android.cattle360.data.demmyModels.cattleModelSupplier
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.home.category.CategoryRepository

class CattleViewModel(private val  repository: CattleRepository) : BaseViewModel() {

    val cattleResponse: LiveData<CattleModel> = MutableLiveData()

    fun getCattleData() {

        cattleResponse as MutableLiveData
        cattleResponse.value = cattleModelSupplier.cattle
    }

}