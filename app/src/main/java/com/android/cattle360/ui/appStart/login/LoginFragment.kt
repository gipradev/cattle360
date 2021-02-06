package com.android.cattle360.ui.appStart.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.LoginFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar


class LoginFragment : BaseFragment<LoginViewModel, LoginFragmentBinding, LoginRepository>() {

    var validPhone: Boolean? = false
    // val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("pref",Context.MODE_PRIVATE)

    override  fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LoginFragmentBinding {
        return LoginFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun getFragmentRepository(): LoginRepository {
        return LoginRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.phoneButton.setOnClickListener {
            //println("Clicked ")
            binding.mobileEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {

                    val mobile = s.toString()
                    if (TextUtils.isEmpty(mobile)) {
                        binding.numberTextField.requestFocus();
                        binding.numberTextField.setError("Please enter mobile number");
                        // Log.e("Validation", "Enter Mobile No or Email")
                    } else {
                        validPhone = false
                        binding.numberTextField.requestFocus();
                        binding.numberTextField.setError("Invalid mobile number");
                        // Log.e("Validation", "Invalid Mobile No")
                    }
                }

            })

            viewModel.sendPhoneNumber(binding.mobileEditText.text.toString())
            viewModel.otpResponse.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is Resource.Loading -> {
                        println("Loading ")
                    }
                    is Resource.Success -> {
                        if (it.value?.status.equals("1") && it.value?.usertype.equals("customer")) {
                            println("Success  : ${it}")
                            val sharedPreference =  requireContext().getSharedPreferences("pref",Context.MODE_PRIVATE)
                            val mobileno= binding.mobileEditText.text.toString()
                            println("mob.........................noo$mobileno")
                            var editor = sharedPreference.edit()
                            editor.putString("mobileno",mobileno)
                            editor.apply()
                            editor.commit()
                            NavHostFragment.findNavController(this)
                                .navigate(R.id.action_loginFragment_to_otpFragment)
                        } else if (it.value?.status.equals("0") ) {
                            println("login  : ${it}")
                            val sharedPreference =  requireContext().getSharedPreferences("pref",Context.MODE_PRIVATE)
                            val mobileno= binding.mobileEditText.text.toString()
                            println("mob.........................noo$mobileno")
                            var editor = sharedPreference.edit()
                            editor.putString("mobileno",mobileno)
                            editor.apply()
                            editor.commit()
                            NavHostFragment.findNavController(this)
                                .navigate(R.id.action_loginFragment_to_passwordFragment)
                        } else {
                            Snackbar.make(
                                requireView(),
                                "${it.value?.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }

                    }
                    is Resource.Failure -> {
                        println("Failure  : ${it}")
                    }

                }
            })

        }

        binding.phoneButton.setOnLongClickListener {

            return@setOnLongClickListener true
        }
    }

}
//
//private fun String.matches(regex: String): Boolean {
//    return Patterns.PHONE.matcher(regex).matches()
//}


