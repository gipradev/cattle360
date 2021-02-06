package com.android.cattle360.ui.appStart.password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetLoginResponse
import com.android.cattle360.data.apiResponse.GetOtpResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PasswordViewModel(private val  repository: PasswordRepository) : BaseViewModel() {
    // TODO: Implement the ViewModel
    val passResponse : LiveData<Resource<GetLoginResponse?>> = MutableLiveData()

    fun login(mobile: String,password: String) = viewModelScope.launch {
        println("On View model ")

        passResponse as MutableLiveData
        passResponse.value = repository.userLogin(mobile, password)
        println("On View model data........................................ ${passResponse.value}")
    }



}