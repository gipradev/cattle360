package com.android.cattle360.ui.executive.addCattle.enterLocation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetAreaListResponse
import com.android.cattle360.data.apiResponse.GetDistrictListResponse
import com.android.cattle360.data.apiResponse.GetStateListResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import kotlinx.coroutines.launch

class EnterLocationViewModel (private  val repository: AddCattleRepository): BaseViewModel() {

    val stateResponse : LiveData<Resource<GetStateListResponse?>> = MutableLiveData()
   // val dataResponse : LiveData<Resource<DataLiveStockDetails?>> = MutableLiveData()


    fun state() = viewModelScope.launch {

        stateResponse as MutableLiveData
        stateResponse.value = Resource.Loading
        stateResponse.value = repository.loadState()
        println("state........................................ ${stateResponse.value}")
    }

    val districtResponse : LiveData<Resource<GetDistrictListResponse?>> = MutableLiveData()

    fun district(state_code: String) = viewModelScope.launch {
        println("On View model ")

        districtResponse as MutableLiveData
      districtResponse.value = Resource.Loading

        districtResponse.value = repository.loadDistrict(state_code)

        println("district....................................... ${districtResponse.value}")
    }


    val areaResponse : LiveData<Resource<GetAreaListResponse?>> = MutableLiveData()

    fun area(district_id: String) = viewModelScope.launch {
        println("On View model ")

        areaResponse as MutableLiveData
        areaResponse.value = repository.loadArea(district_id)
        println("area........................................ ${areaResponse.value}")
    }



}