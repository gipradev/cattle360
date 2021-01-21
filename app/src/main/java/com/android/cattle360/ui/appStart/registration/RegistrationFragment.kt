package com.android.cattle360.ui.appStart.registration

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.databinding.RegistrationFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.HomeActivity

class RegistrationFragment :
    BaseFragment<RegistrationViewModel, RegistrationFragmentBinding, RegistrationRepository>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): RegistrationFragmentBinding {
        return RegistrationFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<RegistrationViewModel> = RegistrationViewModel::class.java

    override fun getFragmentRepository(): RegistrationRepository {
        return RegistrationRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.saveUserButton.setOnClickListener {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

    }

}