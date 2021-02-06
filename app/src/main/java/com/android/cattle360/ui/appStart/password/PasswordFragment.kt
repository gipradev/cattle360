package com.android.cattle360.ui.appStart.password

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.PasswordFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.ExecutiveActivity
import com.android.cattle360.ui.user.HomeActivity
import com.google.android.material.snackbar.Snackbar
import java.util.Observer


class PasswordFragment : BaseFragment<PasswordViewModel, PasswordFragmentBinding, PasswordRepository>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): PasswordFragmentBinding {
        return PasswordFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel():   Class<PasswordViewModel> = PasswordViewModel::class.java

    override fun getFragmentRepository(): PasswordRepository {
        return PasswordRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.submitPassword.setOnClickListener {

            val pref = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
            val mobile = pref.getString("mobileno", "")
            val pass = binding.passwordEditText.text.toString()
            println("$mobile..........$pass")


            viewModel.login(mobile.toString(), binding.passwordEditText.text.toString())
            viewModel.passResponse.observe(viewLifecycleOwner,  {
                when (it) {
                    is Resource.Loading -> {
                        println("Loading ")
                    }
                    is Resource.Success -> {
                        println("${it.value?.status}${it.value?.usertype} ")
                        if (it.value?.status.equals("1") && it.value?.usertype.equals("customer")) {
                            println("Success  : ${it}")
                            Snackbar.make(
                                requireView(),
                                "${it.value?.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                            val intent = Intent(requireContext(), HomeActivity::class.java)
                            startActivity(intent)
                            activity?.finish()
                        } else if (it.value?.status.equals("1") && it.value?.usertype.equals("employee")) {
                            println("Success  : ${it}")
                            Snackbar.make(
                                requireView(),
                                "${it.value?.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                            val intent = Intent(requireContext(), ExecutiveActivity::class.java)
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

