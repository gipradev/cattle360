package com.android.cattle360.ui.user.home.cattleCart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.cattle360.data.demmyModels.CartModel
import com.android.cattle360.data.demmyModels.cartModelSupplier
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.home.category.CategoryRepository

class CattleCartViewModel(private val  repository: CattleCartRepository) : BaseViewModel() {

    val cartResponse: LiveData<CartModel> = MutableLiveData()

    fun getCartData() {
        cartResponse as MutableLiveData
        cartResponse.value = cartModelSupplier.cart
    }

}