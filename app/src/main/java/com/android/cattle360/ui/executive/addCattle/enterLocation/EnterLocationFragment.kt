package com.android.cattle360.ui.executive.addCattle.enterLocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.databinding.EnterLocationFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository

class EnterLocationFragment :
    BaseFragment<EnterLocationViewModel, EnterLocationFragmentBinding, AddCattleRepository>() {

    companion object {
        fun newInstance() = EnterLocationFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): EnterLocationFragmentBinding {
        return EnterLocationFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<EnterLocationViewModel> {
        return EnterLocationViewModel::class.java
    }

    override fun getFragmentRepository(): AddCattleRepository {
        return AddCattleRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.locationNextButton.setOnClickListener {

            NavHostFragment.findNavController(this)
                .navigate(R.id.action_enterLocationFragment_to_enterCattleFragment)

        }

        binding.locationBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

}