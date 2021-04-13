package com.android.cattle360.ui.executive.addCattle.uploadImage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetLogoutResponse
import com.android.cattle360.data.apiResponse.GetRegistrationResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import kotlinx.coroutines.launch

class CattleImageViewModel(private val repository: AddCattleRepository) : BaseViewModel() {

//
//    val insertCattleDetailsResponse : LiveData<Resource<GetLogoutResponse?>> = MutableLiveData()
//    fun insertCattleDetails(pincode_value: String,
//                            area_value: String,
//                            district_value: String,
//                            state_value: String,
//                            title_value: String,
//                            category_value: String,
//                            weight_value: String,
//                            age_value: String,
//                            color_value: String,
//                            biddingamount_value: String,
//                            customerprice_value: String) = viewModelScope.launch {
//        println("On View model ")
//        println("before${insertCattleDetailsResponse.value} ")
//
//        insertCattleDetailsResponse as MutableLiveData
//        insertCattleDetailsResponse.value = repository.onCattleInsert(pincode_value,area_value,district_value,state_value,title_value
//            ,category_value,weight_value,age_value,color_value,biddingamount_value,customerprice_value)
//        println("after" + "${insertCattleDetailsResponse.value} ")
//    }

}