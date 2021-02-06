package com.android.cattle360.ui.appStart.otp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.OtpFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay as coroutinesDelay

class OtpFragment : BaseFragment<OtpViewModel, OtpFragmentBinding, OtpRepository>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): OtpFragmentBinding {
        return OtpFragmentBinding.inflate(layoutInflater, container, false)
    }
    
    override fun getViewModel(): Class<OtpViewModel> = OtpViewModel::class.java

    override fun getFragmentRepository(): OtpRepository {
        return OtpRepository(remoteDataSource.buildApi(ApiService::class.java))

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.otpButton.setOnClickListener {

            viewModel.sendOtp(binding.otpPinView.text.toString())
            viewModel.otpVerificationResponse.observe(
                viewLifecycleOwner,
                androidx.lifecycle.Observer {
                    when (it) {
                        is Resource.Loading -> {
                            println("Loading ")
                        }
                        is Resource.Success -> {
                            if (it.value?.status.equals("1")) {
                                println("Success  : ${it}")
                                //Toast.makeText(activity, "Verified Successfully", Toast.LENGTH_LONG).show()
                                    Snackbar.make(requireView(), "${it.value?.message}", Snackbar.LENGTH_LONG).show()
                                NavHostFragment.findNavController(this@OtpFragment)
                                    .navigate(R.id.action_otpFragment_to_registrationFragment)
                            }
                            else{
                                Snackbar.make(requireView(), "${it.value?.message}", Snackbar.LENGTH_LONG).show()
                            }
                        }
                        is Resource.Failure -> {
                            println("Failure  : ${it}")
                            Snackbar.make(requireView(),"${it}", Snackbar.LENGTH_LONG).show()
                        }

                    }
                })



        }
    }
}