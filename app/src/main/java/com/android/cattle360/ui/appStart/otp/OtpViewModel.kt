package com.android.cattle360.ui.appStart.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetOtpResponse
import com.android.cattle360.data.apiResponse.GetResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class OtpViewModel(private val repository: OtpRepository) : BaseViewModel() {

    val otpVerificationResponse : LiveData<Resource<GetResponse?>> = MutableLiveData()

    fun sendOtp(otp: String) = viewModelScope.launch {
        println("On View model ")

        otpVerificationResponse as MutableLiveData
        otpVerificationResponse.value = repository.otpVerification(otp)
    }
}