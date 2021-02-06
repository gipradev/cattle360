package com.android.cattle360.ui.appStart.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.databinding.SplashFragmentBinding
import com.android.cattle360.ui.base.BaseFragment

class SplashFragment : BaseFragment<SplashViewModel, SplashFragmentBinding, SplashRepository>() {
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

        binding.loginButton.setOnClickListener {
            userTokenValidation()
        }
    }

    private fun userTokenValidation() {


        NavHostFragment.findNavController(this)
            .navigate(R.id.action_splashFragment_to_loginFragment)
    }
}

