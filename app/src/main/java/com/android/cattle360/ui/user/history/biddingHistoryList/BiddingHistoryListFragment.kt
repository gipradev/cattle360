package com.android.cattle360.ui.user.history.biddingHistoryList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
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
        return BiddingRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pref = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val user_id = pref.getString("userid", "")

        binding.biddingHistoryRecycler.adapter = biddingHistoryAdaptor
        viewModel.getBiddingHistory(49.toString())
        viewModel.liveBidHistoryResponse.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Loading -> {
                    println("Loading ")
                }
                is Resource.Success -> {

                    if (it.value?.status.equals("1")) {

                        biddingHistoryAdaptor.list = it.value?.data!!
                        println(".............................history_list"+ it.value.data)

                    }
                    else
                    {
                        println(".............................no data found or error")
                    }
                }

                is Resource.Failure -> {
                    println("Failure  : $it")
                }
            }






        })

    }



}