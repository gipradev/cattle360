package com.android.cattle360.ui.executive.addCattle.uploadImage.uploadFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.Data
import com.android.cattle360.data.apiResponse.GetImageUploadResponse
import com.android.cattle360.data.demmyModels.UploadModel
import com.android.cattle360.data.demmyModels.uploadModelSupplier
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class UploadViewModel (private val repository: AddCattleRepository): BaseViewModel() {
//image listing
    val uploadResponse: LiveData<List<UploadModel>> = MutableLiveData()

    fun getUploadModel() {
        uploadResponse as MutableLiveData
        uploadResponse.value = uploadModelSupplier.modelItem

    }
// image upload
    val imguploadResponse : LiveData<Resource<GetImageUploadResponse?>> = MutableLiveData()

    fun getimageUploadModel(image: RequestBody,model_name: String) = viewModelScope.launch {
        println("On View model ")

        imguploadResponse as MutableLiveData
        imguploadResponse.value = repository.imageUpload(image, model_name)
        println("On View model data........................................ ${imguploadResponse.value}")
    }


}