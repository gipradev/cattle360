package com.android.cattle360.ui.executive.addCattle.enterLocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.apiResponse.Data
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
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
        invalid = false
        stateLoading()
        districtLoading()
        areaLoading()

        addObserver()
        binding.locationNextButton.setOnClickListener(View.OnClickListener {
            invalid = false
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
                    binding.districtEditText.error = "Please! fill your date of birth"

                }
                binding.stateEditText.equals("") -> {
                    invalid = true
                    binding.stateEditText.requestFocusFromTouch();
                    binding.stateEditText.error = "Please! fill your postal address"

                }
                else -> {
                    invalid = false
                    println("invalid false")
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
                    enterLocationFragment.arguments = args

                    NavHostFragment.findNavController(requireParentFragment())
                        .navigate(R.id.action_enterLocationFragment_to_enterCattleFragment)
                }
            }
        })

        binding.locationBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    private fun addObserver() {
        viewModel.stateResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    println("Loading ")
                }
                is Resource.Success -> {
                    if (it.value?.status.equals("1")) {

                        val data: List<Data>? = it.value?.data

                        val stateList = mutableListOf<String>()

                        if (data != null) {
                            for (state in data) {
                                stateList.add(state.state_name)
                            }

                            setStateUi(stateList)


//                            NavHostFragment.findNavController(this)
//                                .navigate(R.id.action_loginFragment_to_otpFragment)

                        }
                    }
                }
                is Resource.Failure -> {
                    println("Failure  : $it")
                }

            }
        })

    }

    private fun areaLoading() {


    }

    private fun districtLoading() {


    }

    private fun stateLoading() = viewModel.state()


    private fun setStateUi(stateList: MutableList<String>) {
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, stateList)
        binding.stateEditText.setAdapter(arrayAdapter)
        binding.stateEditText.setThreshold(1)

    }


}
