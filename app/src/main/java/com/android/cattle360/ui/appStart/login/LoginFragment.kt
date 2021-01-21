package com.android.cattle360.ui.appStart.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.databinding.LoginFragmentBinding
import com.android.cattle360.ui.base.BaseFragment

class LoginFragment : BaseFragment<LoginViewModel, LoginFragmentBinding, LoginRepository>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LoginFragmentBinding {
        return LoginFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun getFragmentRepository(): LoginRepository {
        return LoginRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        binding.phoneButton.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_loginFragment_to_otpFragment)
        }

        binding.phoneButton.setOnLongClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_loginFragment_to_passwordFragment)
            return@setOnLongClickListener true
        }
    }


}