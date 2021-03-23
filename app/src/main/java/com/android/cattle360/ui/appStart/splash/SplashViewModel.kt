package com.android.cattle360.ui.appStart.splash

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetLoginCheckResponse
import com.android.cattle360.data.apiResponse.GetLoginResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: SplashRepository) : BaseViewModel() {
    val loginCheckResponse: LiveData<Resource<GetLoginCheckResponse?>> = MutableLiveData()

    fun loginCheck(mobile: String) = viewModelScope.launch {
        println("On View model ")

        loginCheckResponse as MutableLiveData
        loginCheckResponse.value = repository.userLoginCheck(mobile)

    }

    val employeeloginCheckResponse: LiveData<Resource<GetLoginCheckResponse?>> = MutableLiveData()

    fun employeeloginCheck(username: String) = viewModelScope.launch {
        println("On View model ")

        employeeloginCheckResponse as MutableLiveData
        employeeloginCheckResponse.value = repository.empLoginCheck(username)

    }




}