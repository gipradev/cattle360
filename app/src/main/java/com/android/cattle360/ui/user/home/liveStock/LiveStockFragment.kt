package com.android.cattle360.ui.user.home.liveStock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.databinding.LiveStockFragmentBinding
import com.android.cattle360.ui.base.BaseFragment

class LiveStockFragment :
    BaseFragment<LiveStockViewModel, LiveStockFragmentBinding, LiveStockRepository>(),
    LiveStockAdaptor.OnLiveStockClickEvent {

    private val liveStockAdaptor: LiveStockAdaptor by lazy { LiveStockAdaptor(this) }

    companion object {
        fun newInstance() = LiveStockFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LiveStockFragmentBinding {
        return LiveStockFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<LiveStockViewModel> {
        return LiveStockViewModel::class.java
    }

    override fun getFragmentRepository(): LiveStockRepository {
        return LiveStockRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.liveStockRecycler.adapter = liveStockAdaptor
        viewModel.getLiveStockList()

        viewModel.liveStockResponse.observe(viewLifecycleOwner, Observer {
            liveStockAdaptor.list = it
        })

    }

    override fun onItemClick() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_homeFragment_to_cattleFragment)
    }

    override fun onBidClick() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_homeFragment_to_biddingSheetFragment)
    }


}