package com.android.cattle360.ui.executive.addCattle.enterCattle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.databinding.EnterCattleFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import com.android.cattle360.ui.executive.addCattle.enterLocation.EnterLocationFragment

class EnterCattleFragment :
    BaseFragment<EnterCattleViewModel, EnterCattleFragmentBinding, AddCattleRepository>() {
    var invalid: Boolean = false

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
        return AddCattleRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)









        binding.cattlePreviousButton.setOnClickListener {

            requireActivity().onBackPressed()

        }

        binding.cattleNextButton.setOnClickListener {
            passvalueswithvalidation()


        }

    }

    private fun passvalueswithvalidation() {

        when {
            binding.titleEditText.equals("") -> {
                invalid = true
                binding.titleEditText.requestFocus()
                binding.titleEditText.error = "Title is required"

            }
            binding.categoryEditText.equals("") -> {
                invalid = true
                binding.categoryEditText.requestFocusFromTouch();
                binding.categoryEditText.error = "Category is required"

            }
            binding.weightEditText.equals("") -> {
                invalid = true
                binding.weightEditText.requestFocusFromTouch();
                binding.weightEditText.error = "Weight is required"

            }
            binding.ageEditText.equals("") -> {
                invalid = true
                binding.ageEditText.requestFocusFromTouch();
                binding.ageEditText.error = "Age is required"

            }
            binding.colorEditText.equals("") -> {
                invalid = true
                binding.colorEditText.requestFocusFromTouch();
                binding.colorEditText.error = "Color is required"

            }
            binding.biddingAmountEditText.equals("") -> {
                invalid = true
                binding.biddingAmountEditText.requestFocusFromTouch();
                binding.biddingAmountEditText.error = "Bidding Amount is required"

            }
            binding.customerPriceEditText.equals("") -> {
                invalid = true
                binding.customerPriceEditText.requestFocusFromTouch();
                binding.customerPriceEditText.error = "Customer Price is required"

            }
            else -> {


                invalid = false
                val title_value = binding.titleEditText.text.toString()
                val category_value = binding.categoryEditText.text.toString()
                val weight_value = binding.weightEditText.text.toString()
                val age_value = binding.ageEditText.text.toString()
                val color_value = binding.colorEditText.text.toString()
                val biddingamount_value = binding.biddingAmountEditText.text.toString()
                val customerprice_value = binding.customerPriceEditText.text.toString()

                val pincode_value = arguments?.getString("pincode_value")
                val area_value = arguments?.getString("area_value")
                val district_value = arguments?.getString("district_value")
                val state_value = arguments?.getString("state_value")
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

}