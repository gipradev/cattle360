package com.android.cattle360.ui.executive.addCattle

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository

class AddCattleRepository (private var api : ApiService?= null): BaseRepository() {

    suspend fun loadState() = safeApiCall {
        println("On ApiService ")
        api?.stateAPI()
    }

//    suspend fun loadDistrict(state_name: String) = safeApiCall {
//        println("On ApiService ")
//        api?.districtAPI(state_name)
//    }
//
//    suspend fun loadArea(district_name: String) = safeApiCall {
//        println("On ApiService ")
//        api?.areaAPI(district_name)
//    }
//    suspend fun onCattleInsert(pincode_value: String,
//                               area_value: String,
//                               district_value: String,
//                               state_value: String,
//                               title_value: String,
//                               category_value: String,
//                               weight_value: String,
//                               age_value: String,
//                               color_value: String,
//                               biddingamount_value: String,
//                               customerprice_value: String) = safeApiCall{
//        api?.insertCattleDetailsAPI(pincode_value,area_value,district_value,state_value,title_value,category_value,
//            weight_value,age_value,color_value,biddingamount_value,customerprice_value)
//
//}


}