package com.android.cattle360.ui.executive.addCattle.enterLocation

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.databinding.EnterLocationFragmentBinding

import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository

class EnterLocationFragment :
    BaseFragment<EnterLocationViewModel, EnterLocationFragmentBinding, AddCattleRepository>() {
    var invalid: Boolean = false

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
        return AddCattleRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        binding.pincodeEditText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(
//                s: CharSequence,
//                start: Int,
//                count: Int,
//                after: Int
//            ) {
//            }
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//
//            }
//
//            override fun afterTextChanged(s: Editable) {
//                println("dfghfjkjfffffffff")
//
//
//                when {
//                    binding.pincodeEditText.equals(null) -> {
//                        invalid = true;
//                        binding.pinCodeLayout.requestFocus()
//                        binding.pinCodeLayout.error = "Zip code is required";
//
//                    }
//                        s.length != 6 ->
//                            binding.pinCodeLayout.error = "Enter a valid zip code !.... Zip code must be 6 digits"
//                        else ->
//                            binding.pinCodeLayout.error = null
//                    }
//            }
//
//
//        })
//        binding.areaEditText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(
//                s: CharSequence,
//                start: Int,
//                count: Int,
//                after: Int
//            ) {
//            }
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//
//            }
//
//            override fun afterTextChanged(s: Editable) {
//                println("dfghfjkjfffffffff")
//                when {
//                    binding.areaEditText.equals(null) -> {
//                        invalid = true;
//                        binding.textInputLayout.requestFocus()
//                        binding.textInputLayout.error = "Area is required";
//
//                    }
//
//                }}
//        })
//
//        binding.districtEditText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(
//                s: CharSequence,
//                start: Int,
//                count: Int,
//                after: Int
//            ) {
//            }
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//
//            }
//
//            override fun afterTextChanged(s: Editable) {
//                println("dfghfjkjfffffffff")
//                when {
//                    binding.districtEditText.equals(null) -> {
//                        invalid = true;
//                        binding.textInputLayout2.requestFocus()
//                        binding.textInputLayout2.error = "District is required";
//
//                    }
//
//                }
//            }
//        })
//        binding.stateEditText.addTextChangedListener(object : TextWatcher {
//
//            override fun beforeTextChanged(
//                s: CharSequence,
//                start: Int,
//                count: Int,
//                after: Int
//            ) {
//            }
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//
//            }
//
//            override fun afterTextChanged(s: Editable) {
//                println("dfghfjkjfffffffff")
//                when {
//                    binding.stateEditText.equals(null) -> {
//                        invalid = true;
//                        binding.textInputLayout3.requestFocus()
//                        binding.textInputLayout3.error = "State is required";
//
//                    }
//
//                }
//            }
//        })


        binding.locationNextButton.setOnClickListener(View.OnClickListener {
validationfun()

        })
        binding.locationBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    private fun validationfun() {

        when {
            binding.pincodeEditText.equals("") -> {
                invalid = true
                binding.pincodeEditText.requestFocus()
                binding.pincodeEditText.error = "Please! fill your sponsors name"

            }
            binding.areaEditText.equals("") -> {
                invalid = true
                binding.areaEditText.requestFocusFromTouch();
                binding.areaEditText.error = "Please! fill your name"

            }
            binding.districtEditText.equals("") -> {
                invalid = true
                binding.districtEditText.requestFocusFromTouch();
                binding.districtEditText.setError("Please! fill your date of birth")

            }
            binding.stateEditText.equals("") -> {
                invalid = true
                binding.stateEditText.requestFocusFromTouch();
                binding.stateEditText.setError("Please! fill your postal address")

            }
            else -> {
                invalid = false
                println("sssssssssssssssssssssssss")
                val pincode_value = binding.pincodeEditText.text.toString()
                val area_value = binding.areaEditText.text.toString()
                val district_value = binding.districtEditText.text.toString()
                val state_value = binding.stateEditText.text.toString()
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
        }

    }


}
