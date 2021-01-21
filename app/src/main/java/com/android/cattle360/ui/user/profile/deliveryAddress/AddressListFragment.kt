package com.android.cattle360.ui.user.profile.deliveryAddress

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.cattle360.R

class AddressListFragment : Fragment() {

    companion object {
        fun newInstance() = AddressListFragment()
    }

    private lateinit var viewModel: AddressListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.address_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddressListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}