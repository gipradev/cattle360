package com.android.cattle360.ui.executive.addCattle.enterCattle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.databinding.EnterCattleFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import com.android.cattle360.ui.executive.addCattle.enterLocation.EnterLocationFragment

class EnterCattleFragment :
    BaseFragment<EnterCattleViewModel, EnterCattleFragmentBinding, AddCattleRepository>() {

    companion object {
        fun newInstance() = EnterCattleFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): EnterCattleFragmentBinding {
        return EnterCattleFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<EnterCattleViewModel> {
        return EnterCattleViewModel::class.java
    }

    override fun getFragmentRepository(): AddCattleRepository {
        return AddCattleRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val title_value=binding.titleEditText.text.toString()
        val category_value=binding.categoryEditText.text.toString()
        val weight_value=binding.weightEditText.text.toString()
        val age_value=binding.ageEditText.text.toString()
        val color_value=binding.colorEditText.text.toString()
        val biddingamount_value=binding.biddingAmountEditText.text.toString()
        val customerprice_value=binding.customerPriceEditText.text.toString()

        val pincode_value = requireArguments().getString("pincode_value")
        val area_value = requireArguments().getString("area_value")
        val district_value = requireArguments().getString("pincode_value")
        val state_value = requireArguments().getString("pincode_value")


        binding.cattlePreviousButton.setOnClickListener {

            requireActivity().onBackPressed()

        }

        binding.cattleNextButton.setOnClickListener {

            val enterCattleFragment = EnterCattleFragment()
            val args = Bundle()
            args.putString("pincode_value", pincode_value)
            args.putString("area_value", area_value)
            args.putString("district_value", district_value)
            args.putString("state_value", state_value)
            args.putString("title_value", title_value)
            args.putString("category_value", category_value)
            args.putString("weight_value", weight_value)
            args.putString("age_value", age_value)
            args.putString("color_value", color_value)
            args.putString("biddingamount_value", biddingamount_value)
            args.putString("customerprice_value", customerprice_value)

            enterCattleFragment.setArguments(args)

            NavHostFragment.findNavController(this)
                .navigate(R.id.action_enterCattleFragment_to_cattleImageFragment)

        }

    }

}