package com.android.cattle360.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.android.cattle360.data.network.RemoteDataSource


abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    protected val remoteDataSource = RemoteDataSource()
    protected lateinit var binding: DB
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//   val factory = ViewModelFactory()
      // viewModel = ViewModelProvider(this, factory).get(getViewModel())
      viewModel = ViewModelProvider(this).get(getViewModel())
        binding = DataBindingUtil.setContentView(this, getBinding())
        setContentView(binding.root)

    }

    abstract fun getViewModel(): Class<VM>
    abstract fun getBinding(): Int
  //  abstract fun getFragmentRepository(): BaseRepository

}