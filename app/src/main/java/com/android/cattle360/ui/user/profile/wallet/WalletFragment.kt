package com.android.cattle360.ui.user.profile.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android.cattle360.databinding.WalletFragmentBinding
import com.android.cattle360.ui.base.BaseFragment

class WalletFragment : BaseFragment<WalletViewModel, WalletFragmentBinding, WalletRepository>() {

    private val transactionAdapter: WalletTransactionAdaptor by lazy { WalletTransactionAdaptor() }


    companion object {
        fun newInstance() = WalletFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): WalletFragmentBinding {
        return WalletFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<WalletViewModel> {
        return WalletViewModel::class.java
    }

    override fun getFragmentRepository(): WalletRepository {
        return WalletRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel.getWalletData()
        viewModel.walletResponse.observe(viewLifecycleOwner, Observer {
            binding.walletModel = it
            binding.transactionRecycler.adapter = transactionAdapter
            transactionAdapter.list = it.transaction
        })
    }


}