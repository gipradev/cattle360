package com.android.cattle360.ui.executive.addCattle

import com.android.cattle360.data.network.ApiService
import com.android.cattle360.ui.base.BaseRepository
import okhttp3.RequestBody

class AddCattleRepository (private var api : ApiService?= null): BaseRepository() {

    suspend fun loadState() = safeApiCall {
        println("On ApiService ")
        api?.stateAPI()
    }

    suspend fun loadDistrict(state_code: String) = safeApiCall {
        println("On ApiService ")
        api?.districtAPI(state_code)
    }

    suspend fun loadArea(district_id: String) = safeApiCall {
        println("On ApiService ")
        api?.areaAPI(district_id )
    }

    suspend fun verifyMobileDealer(mobile: String) = safeApiCall {
        println("On ApiService ")
        api?.verifyMobileAPI(mobile)
    }

    suspend fun allCategory() = safeApiCall {
        println("On ApiService ")
        api?.allCategoryAPI()
    }

    suspend fun imageUpload(image: RequestBody, model_name: String) = safeApiCall {
        println("On ApiService imgupload ")
        api?.imageUploadAPI(image,model_name)
    }
    suspend fun imageView(livestock_id: String,
                          c_type: String) = safeApiCall {
        println("On ApiService img list ")
        api?.imageViewAPI(livestock_id,c_type)
    }

    suspend fun imageList() = safeApiCall {
        println("On ApiService img list ")
        api?.imageListAPI()
    }

    suspend fun onCattleInsert(pincode_value: String,
                               area_value: String,
                               district_value: String,
                               state_value: String,
                               title_value: String,
                               category_value: String,
                               weight_value: String,
                               age_value: String,
                               color_value: String,
                               biddingamount_value: String,
                               customerprice_value: String,
                               dealer_id: String,
                               dealer_account_no: String,
                               dealer_address: String,
                               dealer_bank: String,
                               dealer_c_branch: String,
                               dealer_name: String,
                               dealer_email: String,
                               dealer_ifsc: String,
                               dealer_mobile: String) = safeApiCall{
        api?.insertCattleDetailsAPI(pincode_value,area_value,district_value,state_value,title_value,category_value,
            weight_value,age_value,color_value,biddingamount_value,customerprice_value, dealer_id,dealer_account_no,
            dealer_address, dealer_bank, dealer_c_branch, dealer_name, dealer_email, dealer_ifsc, dealer_mobile)

}


}