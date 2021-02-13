package com.android.cattle360.ui.user.profile

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.se.omapi.Session
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.ProfileFragmentBinding
import com.android.cattle360.ui.appStart.MainActivity
import com.android.cattle360.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import java.lang.System.exit

class ProfileFragment : BaseFragment<ProfileViewModel, ProfileFragmentBinding, ProfileRepository>(),
    View.OnClickListener {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun getViewModel(): Class<ProfileViewModel> {
        return ProfileViewModel::class.java
    }

    override fun getFragmentRepository(): ProfileRepository {
        return ProfileRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ProfileFragmentBinding {
        return ProfileFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.addressLayout.setOnClickListener(this)
        binding.walletHistoryLayout.setOnClickListener(this)
        binding.changePasswordLayout.setOnClickListener(this)
        binding.helpLayout.setOnClickListener(this)
        binding.termsLayout.setOnClickListener(this)
        binding.faqLayout.setOnClickListener(this)
        binding.rateAppLayout.setOnClickListener(this)
        binding.sighOutButton.setOnClickListener(this)
        binding.editButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.addressLayout -> {
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_profileFragment_to_addressListFragment)
            }
            R.id.walletHistoryLayout -> {
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_profileFragment_to_walletFragment)
            }
            R.id.changePasswordLayout -> {
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_profileFragment_to_changePasswordFragment)
            }
            R.id.helpLayout -> {

            }
            R.id.termsLayout -> {

            }
            R.id.faqLayout -> {

            }
            R.id.rateAppLayout -> {

            }
            R.id.sighOutButton -> {
                val pref = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
                val mobile=pref.getString("mobileno", "")
                println("${mobile}")
                viewModel.logout(mobile.toString())
                viewModel.logoutResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                    //  println("sssssssssssssssssssssss ${it}")
                    when (it) {
                        is Resource.Loading -> {
                            println("Loading ${it}")
                        }
                        is Resource.Success -> {
                            if (it.value?.status.equals("1")) {
                                 println("${it}")


                                requireContext().getSharedPreferences("pref", MODE_PRIVATE)
                                    .edit()
                                    .clear()
                                    .apply()
                                exit(0)

//                                val intent = Intent(context, MainActivity::class.java)
//                                startActivity(intent)

                            } else if (it.value?.status.equals("0")) {

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
            R.id.editButton -> {

            }

        }
    }

}