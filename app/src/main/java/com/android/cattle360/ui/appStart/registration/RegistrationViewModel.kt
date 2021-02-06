package com.android.cattle360.ui.appStart.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetRegistrationResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class RegistrationViewModel(private val  repository: RegistrationRepository ): BaseViewModel() {
    // TODO: Implement the ViewModel
    val registrationResponse : LiveData<Resource<GetRegistrationResponse?>> = MutableLiveData()
    fun registration(name: String,
                     lastname: String,
                     mobile: String,
                     email: String,
                     password: String,
                     confirm_password: String) = viewModelScope.launch {
        println("On View model ")
        println("before${registrationResponse.value} ")
        registrationResponse as MutableLiveData
        registrationResponse.value = repository.onRegistration(name,lastname,mobile,email,password,confirm_password)
        println("after" +
                "${registrationResponse.value} ")
    }


}