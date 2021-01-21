package com.android.cattle360.ui.user.bidding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.R
import com.android.cattle360.databinding.BiddingFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.bidding.live.LiveBiddingFragment

class BiddingFragment : BaseFragment<BiddingViewModel, BiddingFragmentBinding,BiddingRepository>() {

    companion object {
        fun newInstance() = BiddingFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BiddingFragmentBinding {
        return BiddingFragmentBinding.inflate(layoutInflater,container,false)
    }

    override fun getViewModel(): Class<BiddingViewModel> {
       return BiddingViewModel::class.java
    }

    override fun getFragmentRepository(): BiddingRepository {
       return BiddingRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        launchLiveBiddingFragment()

//        BottomSheetBehavior.from(binding.liveBiddingFragment).apply {
//            this.state = BottomSheetBehavior.STATE_EXPANDED
//        }


    }

    private fun launchLiveBiddingFragment() {
        activity?.supportFragmentManager!!.beginTransaction().replace(
            R.id.liveBiddingFragment,
            LiveBiddingFragment.newInstance(), LiveBiddingFragment::class.java.toString()
        ).commit()
    }


}