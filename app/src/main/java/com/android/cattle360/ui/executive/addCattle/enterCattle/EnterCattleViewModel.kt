package com.android.cattle360.ui.executive.addCattle.enterCattle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetCategoryResponse
import com.android.cattle360.data.apiResponse.GetViewDealerResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import kotlinx.coroutines.launch

class EnterCattleViewModel (private  val repository: AddCattleRepository): BaseViewModel() {


    val verifyMobileResponse : LiveData<Resource<GetViewDealerResponse ?>> = MutableLiveData()

    fun verifyMobile(mobile: String) = viewModelScope.launch {
        println("On View model ")

        verifyMobileResponse as MutableLiveData
        verifyMobileResponse.value = repository.verifyMobileDealer(mobile)
        println("area........................................ ${verifyMobileResponse.value}")
    }

    val categoryResponse : LiveData<Resource<GetCategoryResponse?>> = MutableLiveData()

    fun category() = viewModelScope.launch {

        categoryResponse as MutableLiveData
        categoryResponse.value = Resource.Loading
        categoryResponse.value = repository.allCategory()
        println("state........................................ ${categoryResponse.value}")
    }

}