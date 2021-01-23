package com.android.cattle360.ui.user.home.cattleCart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android.cattle360.R
import com.android.cattle360.databinding.CattleCartFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.home.cattleCart.cartList.CartListFragment
import com.android.cattle360.ui.user.home.cattleCart.cartPrice.CartPriceFragment

class CattleCartFragment :
    BaseFragment<CattleCartViewModel, CattleCartFragmentBinding, CattleCartRepository>() {

    companion object {
        fun newInstance() = CattleCartFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CattleCartFragmentBinding {
        return CattleCartFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<CattleCartViewModel> {
        return CattleCartViewModel::class.java
    }

    override fun getFragmentRepository(): CattleCartRepository {
        return CattleCartRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getCartData()

        viewModel.cartResponse.observe(viewLifecycleOwner, Observer {
            inflateCartListFragment()
            CartListFragment.cartList = it.cartList
            inflateCartPriceFragment()
            CartPriceFragment.cartPriceData = it.data

        })

    }

    private fun inflateCartListFragment() {
        activity?.supportFragmentManager!!.beginTransaction().replace(
            R.id.cartCattleFragment,
            CartListFragment.newInstance(), CartListFragment::class.java.toString()
        ).commit()
    }

    private fun inflateCartPriceFragment() {
        activity?.supportFragmentManager!!.beginTransaction().replace(
            R.id.priceDetailFragment,
            CartPriceFragment.newInstance(), CartPriceFragment::class.java.toString()
        ).commit()
    }


}