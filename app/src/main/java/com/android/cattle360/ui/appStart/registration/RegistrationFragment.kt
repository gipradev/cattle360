package com.android.cattle360.ui.appStart.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.RegistrationFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.HomeActivity
import com.google.android.material.snackbar.Snackbar
import java.util.Observer

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
        val pref = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val mobile=pref.getString("mobileno", "")
        println("mobile$mobile")
        binding.mobileEditText.text = Editable.Factory.getInstance().newEditable(mobile)

        binding.saveUserButton.setOnClickListener {

            viewModel.registration(binding.firstNameEditText.toString(),
                binding.lastNameEditText.toString(),
                binding.mobileEditText.toString(),
                binding.email.toString(),
                binding.password.toString(),
                binding.confirmPassword.toString())
            viewModel.registrationResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
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
                        } else if (it.value?.status.equals("0")){
                            println("else if 0  : ${it}")
                            Snackbar.make(
                                requireView(),
                                "${it.value?.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                    }
                        else {
                            println("else  : ${it}")
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