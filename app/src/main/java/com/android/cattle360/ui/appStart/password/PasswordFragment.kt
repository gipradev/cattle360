package com.android.cattle360.ui.appStart.password

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.PasswordFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.HomeActivity
import com.google.android.material.snackbar.Snackbar


class PasswordFragment :
    BaseFragment<PasswordViewModel, PasswordFragmentBinding, PasswordRepository>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): PasswordFragmentBinding {
        return PasswordFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<PasswordViewModel> = PasswordViewModel::class.java

    override fun getFragmentRepository(): PasswordRepository {
        return PasswordRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.submitPassword.setOnClickListener {

            val pref = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
            val mobile=pref.getString("mobileno", "")
            println("mobile section sp --- "+mobile)
            viewModel.login(mobile.toString(), binding.passwordEditText.text.toString())

            viewModel.passwordResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                when (it) {
                    is Resource.Loading -> {
                        println("Loading ")
                    }
                    is Resource.Success -> {
                        if (it.value?.status.equals("1")) {
                            println("Success  : ${it}")
                            val intent = Intent(requireContext(), HomeActivity::class.java)
                            startActivity(intent)
                            activity?.finish()
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
    }

}