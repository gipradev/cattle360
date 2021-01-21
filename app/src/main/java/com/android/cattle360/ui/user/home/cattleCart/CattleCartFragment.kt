package com.android.cattle360.ui.user.home.cattleCart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.databinding.CattleCartFragmentBinding
import com.android.cattle360.ui.base.BaseFragment

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
       
    }


}