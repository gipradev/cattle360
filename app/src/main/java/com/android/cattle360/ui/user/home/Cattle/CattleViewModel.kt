package com.android.cattle360.ui.user.home.Cattle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.cattle360.data.demmyModels.CattleModel
import com.android.cattle360.data.demmyModels.cattleModelSupplier
import com.android.cattle360.ui.base.BaseViewModel

class CattleViewModel : BaseViewModel() {

    val cattleResponse: LiveData<CattleModel> = MutableLiveData()

    fun getCattleData() {

        cattleResponse as MutableLiveData
        cattleResponse.value = cattleModelSupplier.cattle
    }

}