package com.android.cattle360.ui.user.history.biddingHistoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android.cattle360.databinding.BiddingHistoryListFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.bidding.BiddingRepository

class BiddingHistoryListFragment :
    BaseFragment<BiddingHistoryListViewModel, BiddingHistoryListFragmentBinding, BiddingRepository>() {

    private val biddingHistoryAdaptor: BiddingHistoryAdaptor by lazy { BiddingHistoryAdaptor() }

    companion object {
        fun newInstance() = BiddingHistoryListFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BiddingHistoryListFragmentBinding {
        return BiddingHistoryListFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<BiddingHistoryListViewModel> {
        return BiddingHistoryListViewModel::class.java
    }

    override fun getFragmentRepository(): BiddingRepository {
        return BiddingRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        binding.biddingHistoryRecycler.adapter = biddingHistoryAdaptor
        viewModel.getBiddingHistory()
        viewModel.liveBidResponse.observe(viewLifecycleOwner, Observer {
            biddingHistoryAdaptor.list = it
        })

    }



}