package com.android.cattle360.ui.executive.addCattle.uploadImage.uploadFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.cattle360.data.demmyModels.UploadModel
import com.android.cattle360.data.demmyModels.uploadModelSupplier
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository

class UploadViewModel (private val repository: AddCattleRepository): BaseViewModel() {

    val uploadResponse: LiveData<List<UploadModel>> = MutableLiveData()

    fun getUploadModel() {
        uploadResponse as MutableLiveData
        uploadResponse.value = uploadModelSupplier.modelItem
}
}