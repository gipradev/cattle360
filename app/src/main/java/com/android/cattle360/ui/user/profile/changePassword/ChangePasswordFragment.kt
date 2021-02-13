package com.android.cattle360.ui.user.profile.changePassword

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.ChangePasswordFragmentBinding
import com.android.cattle360.ui.base.BaseFragment

import com.google.android.material.snackbar.Snackbar

class ChangePasswordFragment :
    BaseFragment<ChangePasswordViewModel, ChangePasswordFragmentBinding, ChangPasswordRepository>(){

    companion object {
        fun newInstance() = ChangePasswordFragment()
    }

    override fun getViewModel(): Class<ChangePasswordViewModel> {
        return ChangePasswordViewModel::class.java
    }

    override fun getFragmentRepository(): ChangPasswordRepository {
        return ChangPasswordRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ChangePasswordFragmentBinding {
        return ChangePasswordFragmentBinding.inflate(layoutInflater, container, false)
    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.change_password_fragment, container, false)
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      //  viewModel = ViewModelProvider(this).get(ChangePasswordViewModel::class.java)

        binding.saveUserButton.setOnClickListener {
            changepwd()
        }

}

    private fun changepwd() {

        val pref = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val mobile=pref.getString("mobileno", "")
        viewModel.onchangePassword(mobile.toString(),binding.password.toString(),binding.confirmPassword.toString())
        viewModel.changepasswordResponse.observe(viewLifecycleOwner,  {
            when (it) {
                is Resource.Loading -> {
                    println("Loading ")
                }
                is Resource.Success -> {
                    println("${it.value?.status} ")
                    if (it.value?.status.equals("1")) {
                        println("Success  : ${it}")
                        Snackbar.make(
                            requireView(),
                            "${it.value?.message}",
                            Snackbar.LENGTH_LONG
                        ).show()
                        NavHostFragment.findNavController(this)
                            .navigate(R.id.action_changePasswordFragment_to_profileFragment)

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