package com.android.cattle360.ui.user.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.R
import com.android.cattle360.databinding.BiddingHistoryFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.bidding.BiddingRepository
import com.android.cattle360.ui.user.history.biddingHistoryList.BiddingHistoryListFragment
import com.android.cattle360.ui.user.history.totalFragment.TotalAmountFragment

class BiddingHistoryFragment :
    BaseFragment<BiddingHistoryViewModel, BiddingHistoryFragmentBinding, BiddingRepository>() {


    companion object {
        fun newInstance() = BiddingHistoryFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BiddingHistoryFragmentBinding {
        return BiddingHistoryFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<BiddingHistoryViewModel> {
        return BiddingHistoryViewModel::class.java
    }

    override fun getFragmentRepository(): BiddingRepository {
        return BiddingRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        launchTotalAmountFragment()
        launchBiddingHistoryListFragment()


    }

    private fun launchTotalAmountFragment() {
        activity?.supportFragmentManager!!.beginTransaction().replace(
            R.id.totalAmountFragment,
            TotalAmountFragment.newInstance(), TotalAmountFragment::class.java.toString()
        ).commit()
    }

    private fun launchBiddingHistoryListFragment() {
        activity?.supportFragmentManager!!.beginTransaction().replace(
            R.id.bidingHistoryFragment,
            BiddingHistoryListFragment.newInstance(),
            BiddingHistoryListFragment::class.java.toString()
        ).commit()
    }

}