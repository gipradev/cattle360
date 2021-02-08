package com.android.cattle360.ui.user.profile.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.cattle360.data.demmyModels.WalletModel
import com.android.cattle360.data.demmyModels.walletModelSupplier
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.home.location.LocationRepository

class WalletViewModel(private val  repository: WalletRepository) : BaseViewModel() {

    val walletResponse: LiveData<WalletModel> = MutableLiveData()

    fun getWalletData() {

        walletResponse as MutableLiveData
        walletResponse.value = walletModelSupplier.wallet
    }


}