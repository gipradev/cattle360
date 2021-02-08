package com.android.cattle360.ui.user.home.banner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.cattle360.data.demmyModels.BannerModel
import com.android.cattle360.data.demmyModels.bannerSupplier
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.bidding.BiddingRepository

class BannerViewModel(private val  repository: BannerRepository) : BaseViewModel() {

    val bannerResponse: LiveData<List<BannerModel>> = MutableLiveData()

    fun getBannerList() {
        bannerResponse as MutableLiveData
        bannerResponse.value = bannerSupplier.bannerItem
    }
}