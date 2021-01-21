package com.android.cattle360.ui.appStart.searchLocation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.databinding.SearchLocationFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.HomeActivity


class SearchLocationFragment :
    BaseFragment<SearchLocationViewModel, SearchLocationFragmentBinding, SearchLocationRepository>() {

    companion object {
        fun newInstance() = SearchLocationFragment()
    }


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SearchLocationFragmentBinding {
        return SearchLocationFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<SearchLocationViewModel> =
        SearchLocationViewModel::class.java

    override fun getFragmentRepository(): SearchLocationRepository {
        return SearchLocationRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.saveAddressButton.setOnClickListener {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

}