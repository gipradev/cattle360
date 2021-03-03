package com.android.cattle360.ui.appStart.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetOtpResponse
import com.android.cattle360.data.apiResponse.GetUsernameResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(private val  repository: LoginRepository) : BaseViewModel() {

    val otpResponse : LiveData<Resource<GetOtpResponse?>> = MutableLiveData()



    fun sendPhoneNumber(mobile: String) = viewModelScope.launch {
        println("On View model ")

        otpResponse as MutableLiveData
       otpResponse.value = repository.sendMobileToService(mobile)
    }


    val usernsmeResponse : LiveData<Resource<GetUsernameResponse?>> = MutableLiveData()

    fun sendUsername(username: String) = viewModelScope.launch {
        println("On View model ")

        usernsmeResponse as MutableLiveData
        usernsmeResponse.value = repository.sendUsernameToService(username)
    }

}