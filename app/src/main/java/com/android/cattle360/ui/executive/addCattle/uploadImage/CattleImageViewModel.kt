package com.android.cattle360.ui.executive.addCattle.uploadImage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetImageUploadResponse
import com.android.cattle360.data.apiResponse.GetImageViewResponse
import com.android.cattle360.data.apiResponse.GetLogoutResponse
import com.android.cattle360.data.apiResponse.GetRegistrationResponse
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class CattleImageViewModel(private val repository: AddCattleRepository) : BaseViewModel() {

    // image upload
    val imguploadResponse : LiveData<Resource<GetImageUploadResponse?>> = MutableLiveData()

    fun getimageUploadModel(image: RequestBody, model_name: String) = viewModelScope.launch {
        println("On View model ")

        imguploadResponse as MutableLiveData
        imguploadResponse.value = repository.imageUpload(image, model_name)
        println("On View model data........................................ ${imguploadResponse.value}")
    }

    //imageView

    val imgViewResponse : LiveData<Resource<GetImageViewResponse?>> = MutableLiveData()

    fun imageViewModel(livestock_id: String,c_type: String) = viewModelScope.launch {
        println("On View model ")
        imgViewResponse as MutableLiveData
        imgViewResponse.value = repository.imageView(livestock_id, c_type)
        println("On View model data........................................ ${imgViewResponse.value}")
    }

}