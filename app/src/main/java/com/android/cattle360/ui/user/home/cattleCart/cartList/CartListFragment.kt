package com.android.cattle360.ui.user.home.cattleCart.cartList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.cattle360.data.demmyModels.CartListModel
import com.android.cattle360.databinding.CartListFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.home.Cattle.CattleRepository

class CartListFragment :
    BaseFragment<CartListViewModel, CartListFragmentBinding, CattleRepository>(),
    CartListAdaptor.OnCartListClickEvent {

    private val cartListAdaptor: CartListAdaptor by lazy { CartListAdaptor(this) }

    companion object {
        fun newInstance() = CartListFragment()
        var cartList: List<CartListModel> = arrayListOf()
            set(value) {
                field = value
            }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CartListFragmentBinding {
        return CartListFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<CartListViewModel> {
        return CartListViewModel::class.java
    }

    override fun getFragmentRepository(): CattleRepository {
        return CattleRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        binding.cartListRecycler.adapter = cartListAdaptor
        binding.cartListRecycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        cartListAdaptor.list = cartList

    }

    override fun onDeleteClick() {

    }


}