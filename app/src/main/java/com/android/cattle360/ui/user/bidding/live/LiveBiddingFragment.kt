package com.android.cattle360.ui.user.bidding.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.CattleFragmentBinding
import com.android.cattle360.databinding.LiveBiddingFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.bidding.BiddingRepository
import com.android.cattle360.ui.user.home.Cattle.CattleRepository
import com.android.cattle360.ui.user.home.Cattle.CattleViewModel
import com.android.cattle360.ui.user.home.Cattle.cattleImageSlider.ImageSliderFragment

class LiveBiddingFragment : BaseFragment<LiveBiddingViewModel, LiveBiddingFragmentBinding, BiddingRepository>( ){

    companion object {
        fun newInstance() = LiveBiddingFragment()
    }

    private val liveBidAdaptor: LiveBidAdaptor by lazy { LiveBidAdaptor() }


 //   private lateinit var binding: LiveBiddingFragmentBinding
  //  private lateinit var viewModel: LiveBiddingViewModel

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LiveBiddingFragmentBinding {
        return LiveBiddingFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<LiveBiddingViewModel> {
        return LiveBiddingViewModel::class.java
    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = DataBindingUtil.inflate(
//            layoutInflater,
//            R.layout.live_bidding_fragment,
//            container,
//            false)
//
//        viewModel = ViewModelProvider(this).get(LiveBiddingViewModel::class.java)
//        return binding.root
//    }
override fun getFragmentRepository(): BiddingRepository {
    return BiddingRepository(remoteDataSource.buildApi(ApiService::class.java))
}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



//need to be edit
//        viewModel.getCattleData(livestock_id.toString())
//        viewModel.cattleResponse.observe(viewLifecycleOwner, Observer {
//
//            val i = Bundle()
//            i.putString("livestock_id", livestock_id)
//
//            when (it) {
//                is Resource.Loading -> {
//                    println("Loading")
//                }
//                is Resource.Success -> {
//
//                    if (it.value?.status.equals("1")) {
//
//                        //ImageSliderFragment.imageiist = it.product_image
//                        binding.cattleDataModel = it.value
//                        // println("debug ${it.product_image}")
//
//
//                    } else {
//                        println(".............................no data found or error")
//                    }
//                }
//
//                is Resource.Failure -> {
//                    println("Failure  : $it")
//                }
//
//            }
//
//        })

   //  ..................
        binding.liveStockRecycler.adapter = liveBidAdaptor

        viewModel.getLiveBiddingList()
        viewModel.liveBidResponse.observe(viewLifecycleOwner, Observer {
            liveBidAdaptor.list = it

        })
    }

}