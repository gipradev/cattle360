package com.android.cattle360.ui.user.bidding.live

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.LiveBiddingFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.bidding.BiddingRepository


class LiveBiddingFragment : BaseFragment<LiveBiddingViewModel, LiveBiddingFragmentBinding, BiddingRepository>( ){

    companion object {
        fun newInstance() = LiveBiddingFragment()
    }
  //  val pref = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
  lateinit var pref:SharedPreferences
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

        val user_id = pref.getString("userid", "")
        binding.liveStockRecycler.adapter = liveBidAdaptor
        //user_id.toString()
        viewModel.getLiveBiddingList(49.toString())
        viewModel.liveBidResponse.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Loading -> {
                    println("Loading ")
                }
                is Resource.Success -> {

                    if (it.value?.status.equals("1")) {

                        liveBidAdaptor.list = it.value?.data!!
                        println(".............................list" + it.value.data)

                    } else {
                        println(".............................no data found or error")
                    }
                }

                is Resource.Failure -> {
                    println("Failure  : $it")
                }
            }

        })
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        pref = context.getSharedPreferences("pref", 0)
    }
}