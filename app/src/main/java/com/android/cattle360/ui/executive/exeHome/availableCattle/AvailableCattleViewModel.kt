package com.android.cattle360.ui.executive.exeHome.availableCattle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.cattle360.data.demmyModels.AvailableCattleModel
import com.android.cattle360.data.demmyModels.availableModelSupplier
import com.android.cattle360.ui.base.BaseViewModel

class AvailableCattleViewModel : BaseViewModel() {

    val cattleResponse: LiveData<List<AvailableCattleModel>> = MutableLiveData()

    fun getCattleList() {
        cattleResponse as MutableLiveData
        cattleResponse.value = availableModelSupplier.availableCattleItem
    }
}