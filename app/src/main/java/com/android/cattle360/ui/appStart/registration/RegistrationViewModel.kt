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

    fun registration(Name: String,
                     Last_Name: String,
                     mobile: String,
                     email: String,
                     password: String,
                     confirm_password: String) = viewModelScope.launch {
        println("On View model ")

        registrationResponse as MutableLiveData
        registrationResponse.value = repository.onRegistration(Name,Last_Name,mobile,email,password,confirm_password)
    }


}