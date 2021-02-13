package com.android.cattle360.ui.user.profile.changePassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetLogoutResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.home.location.LocationRepository
import com.android.cattle360.ui.user.profile.ProfileRepository
import kotlinx.coroutines.launch

class ChangePasswordViewModel(private  val repository: ChangPasswordRepository) : BaseViewModel() {

    val changepasswordResponse : LiveData<Resource<GetLogoutResponse?>> = MutableLiveData()
    fun onchangePassword(mobile: String,
               current_password: String,
               new_password: String) = viewModelScope.launch {
        println("On View model ")
        //  println("before${registrationResponse.value} ")
        changepasswordResponse as MutableLiveData
        changepasswordResponse.value = repository.changePassword(mobile,current_password,new_password)

    }

}