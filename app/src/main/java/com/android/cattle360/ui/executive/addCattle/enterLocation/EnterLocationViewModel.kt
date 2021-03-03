package com.android.cattle360.ui.executive.addCattle.enterLocation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetLoginResponse
import com.android.cattle360.data.apiResponse.GetLogoutResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import kotlinx.coroutines.launch

class EnterLocationViewModel (private  val repository: AddCattleRepository): BaseViewModel() {


    val districtResponse : LiveData<Resource<GetLogoutResponse?>> = MutableLiveData()

    fun district(state_name: String) = viewModelScope.launch {
        println("On View model ")

        districtResponse as MutableLiveData
        districtResponse.value = repository.loadDistrict(state_name)
        println("On View model data........................................ ${districtResponse.value}")
    }

    val stateResponse : LiveData<Resource<GetLogoutResponse?>> = MutableLiveData()

    fun state() = viewModelScope.launch {
        println("On View model ")

        stateResponse as MutableLiveData
        stateResponse.value = repository.loadState()
        println("On View model data........................................ ${stateResponse.value}")
    }



    private val areaResponse : LiveData<Resource<GetLogoutResponse?>> = MutableLiveData()

    fun area(district_name: String) = viewModelScope.launch {
        println("On View model ")

        areaResponse as MutableLiveData
        areaResponse.value = repository.loadArea(district_name)
        println("On View model data........................................ ${areaResponse.value}")
    }



}