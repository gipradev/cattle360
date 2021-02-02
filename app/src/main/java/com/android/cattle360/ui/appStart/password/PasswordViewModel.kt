package com.android.cattle360.ui.appStart.password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetLoginResponse
import com.android.cattle360.data.apiResponse.GetRegistrationResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.appStart.login.LoginRepository
import com.android.cattle360.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class PasswordViewModel(private val  repository: PasswordRepository) : BaseViewModel() {
    // TODO: Implement the ViewModel
    val passwordResponse : LiveData<Resource<GetLoginResponse?>> = MutableLiveData()

    fun login(mobile: String,
                     password: String) = viewModelScope.launch {
        println("On View model ")

        passwordResponse as MutableLiveData
        passwordResponse.value = repository.userLogin(mobile,password)
    }



}