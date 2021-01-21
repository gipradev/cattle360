package com.android.cattle360.ui.user.history.totalFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.cattle360.R

class TotalAmountFragment : Fragment() {

    companion object {
        fun newInstance() = TotalAmountFragment()
    }

    private lateinit var viewModel: TotalAmountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.toatl_amount_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TotalAmountViewModel::class.java)
        // TODO: Use the ViewModel
    }

}