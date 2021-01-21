package com.android.cattle360.ui.user.bidding.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.cattle360.R
import com.android.cattle360.databinding.LiveBiddingFragmentBinding

class LiveBiddingFragment : Fragment() {

    companion object {
        fun newInstance() = LiveBiddingFragment()
    }

    private val liveBidAdaptor: LiveBidAdaptor by lazy { LiveBidAdaptor() }


    private lateinit var binding: LiveBiddingFragmentBinding
    private lateinit var viewModel: LiveBiddingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.live_bidding_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(LiveBiddingViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.liveStockRecycler.adapter = liveBidAdaptor

        viewModel.getLiveBiddingList()
        viewModel.liveBidResponse.observe(viewLifecycleOwner, Observer {

            liveBidAdaptor.list = it
        })
    }

}