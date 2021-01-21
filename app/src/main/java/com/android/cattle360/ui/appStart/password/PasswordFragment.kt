package com.android.cattle360.ui.appStart.password

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.databinding.PasswordFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.HomeActivity

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
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)

        }
    }

}