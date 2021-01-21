package com.android.cattle360.ui.appStart.otp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.databinding.OtpFragmentBinding
import com.android.cattle360.ui.base.BaseFragment

class OtpFragment : BaseFragment<OtpViewModel, OtpFragmentBinding, OtpRepository>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): OtpFragmentBinding {
        return OtpFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<OtpViewModel> = OtpViewModel::class.java

    override fun getFragmentRepository(): OtpRepository {
        return OtpRepository()
        //remoteDataSource.buildApi(ApiService::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.otpButton.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_otpFragment_to_registrationFragment)
        }
    }
}