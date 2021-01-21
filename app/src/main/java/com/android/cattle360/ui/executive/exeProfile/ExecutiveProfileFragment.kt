package com.android.cattle360.ui.executive.exeProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.databinding.ExecutiveProfileFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.ExecutiveRepository

class ExecutiveProfileFragment :
    BaseFragment<ExecutiveProfileViewModel, ExecutiveProfileFragmentBinding, ExecutiveRepository>() {

    companion object {
        fun newInstance() = ExecutiveProfileFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ExecutiveProfileFragmentBinding {
        return ExecutiveProfileFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<ExecutiveProfileViewModel> {
        return ExecutiveProfileViewModel::class.java
    }

    override fun getFragmentRepository(): ExecutiveRepository {
        return ExecutiveRepository()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}