package com.android.cattle360.ui.user.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetLogoutResponse
import com.android.cattle360.data.apiResponse.GetRegistrationResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository : ProfileRepository): BaseViewModel() {

    val logoutResponse : LiveData<Resource<GetLogoutResponse?>> = MutableLiveData()
    fun logout(mobile : String) = viewModelScope.launch {
        println("On View model ")
      //  println("before${registrationResponse.value} ")
        logoutResponse as MutableLiveData
        logoutResponse.value = repository.onLogout(mobile)

    }

}