package com.android.cattle360.ui.user.home.cattleCart.cartPrice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.data.demmyModels.CartDataModel
import com.android.cattle360.databinding.CartPriceFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.home.cattleCart.CattleCartRepository

class CartPriceFragment :
    BaseFragment<CartPriceViewModel, CartPriceFragmentBinding, CattleCartRepository>() {

    companion object {
        fun newInstance() = CartPriceFragment()
        var cartPriceData: CartDataModel? = null
            set(value) {
                field = value
            }

    }


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CartPriceFragmentBinding {
        return CartPriceFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<CartPriceViewModel> {
        return CartPriceViewModel::class.java
    }

    override fun getFragmentRepository(): CattleCartRepository {
        return CattleCartRepository()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.model = cartPriceData

    }

}