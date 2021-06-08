package com.android.cattle360.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.cattle360.data.network.RemoteDataSource


abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding, R : BaseRepository> :
    Fragment() {



    protected lateinit var binding: DB
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
      //viewModel = ViewModelProvider(this).get(getViewModel())
        return binding.root
    }


    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): DB
    abstract fun getViewModel(): Class<VM>
    abstract fun getFragmentRepository(): R

}