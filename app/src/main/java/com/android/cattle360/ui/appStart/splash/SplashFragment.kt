package com.android.cattle360.ui.appStart.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.SplashFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.ExecutiveActivity
import com.android.cattle360.ui.user.HomeActivity
import com.google.android.material.snackbar.Snackbar

class SplashFragment : BaseFragment<SplashViewModel, SplashFragmentBinding, SplashRepository>() {

   // var mobile: String? =null

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SplashFragmentBinding {
        return SplashFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<SplashViewModel> = SplashViewModel::class.java
    override fun getFragmentRepository(): SplashRepository {
        return SplashRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userTokenValidation()
        binding.loginButton.setOnClickListener {
            userTokenValidation()
        }
    }

    private fun userTokenValidation() {
        val pref = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val mobile = pref.getString("mobileno", "")
        viewModel.loginCheck(mobile.toString())
        viewModel.loginCheckResponse.observe(viewLifecycleOwner,  {
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
                        NavHostFragment.findNavController(this)
                            .navigate(R.id.action_splashFragment_to_loginFragment)
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

