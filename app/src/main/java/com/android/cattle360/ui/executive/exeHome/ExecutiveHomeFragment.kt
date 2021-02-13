package com.android.cattle360.ui.executive.exeHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.R
import com.android.cattle360.databinding.ExecutiveHomeFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.exeHome.availableCattle.AvailableCattleFragment

class ExecutiveHomeFragment :
    BaseFragment<ExecutiveHomeViewModel, ExecutiveHomeFragmentBinding, ExecutiveHomeRepository>() {

    companion object {
        fun newInstance() = ExecutiveHomeFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ExecutiveHomeFragmentBinding {
        return ExecutiveHomeFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<ExecutiveHomeViewModel> {
        return ExecutiveHomeViewModel::class.java
    }

    override fun getFragmentRepository(): ExecutiveHomeRepository {
        return ExecutiveHomeRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        launchCattleListFragment()

    }

    private fun launchCattleListFragment() {
        activity?.supportFragmentManager!!.beginTransaction().replace(
            R.id.cattleListFragment,
            AvailableCattleFragment.newInstance(), AvailableCattleFragment::class.java.toString()
        ).commit()
    }


}