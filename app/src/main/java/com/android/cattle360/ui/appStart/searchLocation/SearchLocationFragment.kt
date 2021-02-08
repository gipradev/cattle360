package com.android.cattle360.ui.appStart.searchLocation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.libraries.places.api.Places
import com.android.cattle360.R
import com.android.cattle360.databinding.SearchLocationFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.HomeActivity
import com.google.android.gms.location.places.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import java.util.*


class SearchLocationFragment : BaseFragment<SearchLocationViewModel, SearchLocationFragmentBinding, SearchLocationRepository>() {




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

        val apiKey = getString(R.string.api_key)
        if (!Places.isInitialized()) {
            Places.initialize(requireActivity().getApplicationContext(), apiKey)
        }
        val placesClient: PlacesClient = Places.createClient(requireActivity())

//     AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
//        binding.autocompleteFragment.getSupportFragmentManager()
//
//        binding.autocompleteFragment.setPlaceFields(Arrays.asList(com.google.android.libraries.places.api.model.Place.Field.ID, com.google.android.libraries.places.api.model.Place.Field.NAME));
//
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                // TODO: Get info about the selected place.
//                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
//            }
//
//            @Override
//            public void onError(Status status) {
//                // TODO: Handle the error.
//                Log.i(TAG, "An error occurred: " + status);
//            }
//        });
//
        binding.saveAddressButton.setOnClickListener {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }


    }

    }



