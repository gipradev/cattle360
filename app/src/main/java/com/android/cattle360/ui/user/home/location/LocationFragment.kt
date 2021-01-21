package com.android.cattle360.ui.user.home.location

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.databinding.LocationFragmentBinding
import com.android.cattle360.ui.base.BaseFragment


class LocationFragment :
    BaseFragment<LocationViewModel, LocationFragmentBinding, LocationRepository>() {

    companion object {
        fun newInstance() = LocationFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LocationFragmentBinding {
        return LocationFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<LocationViewModel> = LocationViewModel::class.java

    override fun getFragmentRepository(): LocationRepository {
        return LocationRepository()
    }

    @SuppressLint("ResourceType")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.location.setOnClickListener {

            NavHostFragment.findNavController(this)
                .navigate(R.id.action_homeFragment_to_searchLocationFragment)

        }

    }


}