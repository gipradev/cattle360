package com.android.cattle360.ui.user.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.transition.TransitionInflater
import com.android.cattle360.R
import com.android.cattle360.databinding.ProfileFragmentBinding
import com.android.cattle360.ui.base.BaseFragment

class ProfileFragment : BaseFragment<ProfileViewModel, ProfileFragmentBinding, ProfileRepository>(),
    View.OnClickListener {

    companion object {
        fun newInstance() = ProfileFragment()
    }


    override fun getViewModel(): Class<ProfileViewModel> {
        return ProfileViewModel::class.java
    }

    override fun getFragmentRepository(): ProfileRepository {
        return ProfileRepository()
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

            }
            R.id.editButton -> {

            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition( R.transition.fade)
        enterTransition = inflater.inflateTransition( R.transition.slide_right)
    }

}