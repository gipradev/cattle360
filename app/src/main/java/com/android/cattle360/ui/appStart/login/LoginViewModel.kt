package com.android.cattle360.ui.appStart.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetOtpResponse
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
}