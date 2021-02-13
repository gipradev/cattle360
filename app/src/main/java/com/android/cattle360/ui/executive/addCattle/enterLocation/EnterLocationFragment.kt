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

        val pincode_value=binding.pincodeEditText.text.toString()
        val area_value=binding.areaEditText.text.toString()
        val district_value=binding.districtEditText.text.toString()
        val state_value=binding.stateEditText.text.toString()

        binding.locationNextButton.setOnClickListener {
            val enterLocationFragment = EnterLocationFragment()
            val args = Bundle()
            args.putString("pincode_value", pincode_value)
            args.putString("area_value", area_value)
            args.putString("district_value", district_value)
            args.putString("state_value", state_value)
            enterLocationFragment.setArguments(args)

            NavHostFragment.findNavController(this)
                .navigate(R.id.action_enterLocationFragment_to_enterCattleFragment)

        }

        binding.locationBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

}