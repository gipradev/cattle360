package com.android.cattle360.ui.appStart.searchLocation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.android.cattle360.R
import com.android.cattle360.databinding.SearchLocationFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.HomeActivity
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener


class SearchLocationFragment : BaseFragment<SearchLocationViewModel, SearchLocationFragmentBinding, SearchLocationRepository>() {

    private val AUTOCOMPLETE_REQUEST_CODE = 1
    private val TAG = "SearchLocationFragment"

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
        if (!Places.isInitialized()) {
            Places.initialize(requireActivity(), "AIzaSyBea7Nim66YQ4VWTlPoSSj5N4pSghbNNrY");
        }
        // Initialize the AutocompleteSupportFragment.

        val autocompleteFragment =
            childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment?
        // Specify the types of place data to return.
       autocompleteFragment?.setPlaceFields(
           listOf(
               Place.Field.ID,
               Place.Field.NAME))
        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment?.setOnPlaceSelectedListener(object :
            PlaceSelectionListener {

            override fun onPlaceSelected(place : Place ) {

                if(place.getAddressComponents()?.asList()?.get(0)?.getTypes()?.get(0).equals("YOUR STRING", ignoreCase = true)){
                    binding.placeSearch.text=place.getAddress()//Works well
                    var location = place.getAddress()

                }else{ //If user does not choose a specific place.
                    Toast.makeText(requireActivity(), getString(R.string.choose_an_address), Toast.LENGTH_LONG).show()
                }
             //   Log.i("Places", place.name)
               Log.i(TAG, "Place: " + place.getAddressComponents()!!.asList().get(0).getTypes().get(0) + ", " + place.getId() + ", " + place.getAddress());

            }

            override fun onError(status: Status) {

                Log.i(TAG,"An error occurred: $status")
            }
        })

        binding.saveAddressButton.setOnClickListener {
//            val fields = listOf(Place.Field.ID, Place.Field.NAME)
//            val intentf = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
//                .setTypeFilter(TypeFilter.ADDRESS)
//                .build(requireActivity())
//            startActivityForResult(intentf, AUTOCOMPLETE_REQUEST_CODE)

           val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        Log.i("TAG", "Place: ${place.name}, ${place.id}")
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        status.statusMessage?.let { it1 -> Log.i("TAG", it1) }
                    }
                }
                Activity.RESULT_CANCELED -> {
                    // The user canceled the operation.
                }
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
}
    }







