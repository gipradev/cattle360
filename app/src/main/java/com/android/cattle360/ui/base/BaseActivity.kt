package com.android.cattle360.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider


abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: DB
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
        binding = DataBindingUtil.setContentView(this, getBinding())
        setContentView(binding.root)

    }


    abstract fun getViewModel(): Class<VM>
    abstract fun getBinding(): Int

}