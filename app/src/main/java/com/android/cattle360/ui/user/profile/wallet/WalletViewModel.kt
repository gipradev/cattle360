package com.android.cattle360.ui.user.profile.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetTokenResponse
import com.android.cattle360.data.demmyModels.WalletModel
import com.android.cattle360.data.demmyModels.walletModelSupplier
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class WalletViewModel(private val  repository: WalletRepository) : BaseViewModel() {

    val walletResponse: LiveData<WalletModel> = MutableLiveData()

    fun getWalletData() {

        walletResponse as MutableLiveData
        walletResponse.value = walletModelSupplier.wallet
    }
    val getTokenResponse : LiveData<Resource<GetTokenResponse?>> = MutableLiveData()
    fun getTokenfun(
        code: String,
        MID: String,
        ORDER_ID: String,
        AMOUNT: String) = viewModelScope.launch {
        println("On View model ")
        println("before${getTokenResponse.value} ")
        getTokenResponse as MutableLiveData
        getTokenResponse.value = repository.onGetToken(code,MID,ORDER_ID,AMOUNT)

}}