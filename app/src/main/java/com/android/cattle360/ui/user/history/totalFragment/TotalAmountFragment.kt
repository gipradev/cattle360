package com.android.cattle360.ui.user.history.totalFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.RemoteDataSource
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.BiddingHistoryListFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.base.ViewModelFactory
import com.android.cattle360.ui.user.bidding.BiddingRepository
import com.android.cattle360.ui.user.history.biddingHistoryList.BiddingHistoryAdaptor
import com.android.cattle360.ui.user.history.biddingHistoryList.BiddingHistoryListViewModel
import com.android.cattle360.ui.user.home.Cattle.CattleRepository

class TotalAmountFragment : Fragment() {

    companion object {
        fun newInstance() = TotalAmountFragment()
    }

 lateinit var viewModel: TotalAmountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.toatl_amount_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this,
            ViewModelFactory(BiddingRepository(RemoteDataSource().buildApi(ApiService::class.java)))
        ).get(TotalAmountViewModel::class.java)


        viewModel.getBiddingHistorytotal(49.toString())
        viewModel.liveBidHistorytotalResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            println("...................................................it"+it)
            when (it) {
                is Resource.Loading -> {
                    println("Loading")
                    Toast.makeText(
                        requireContext(),
                        "Loading..",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Resource.Success -> {

                    if (it.value?.status.equals("1")) {
                        val textView = view?.findViewById<TextView>(R.id.materialTextView5)
                        textView?.setText( " ₹ " +it.value?.toal_bid_amount).toString()
                        val textView1 = view?.findViewById<TextView>(R.id.text22)
                        textView1?.setText( " ₹ " +it.value?.toal_bid_amount).toString()

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "no data",
                            Toast.LENGTH_LONG
                        ).show()
                      //  println("..........................else msg..${it.value?.toal_bid_amount}.")
                    }
                }

                is Resource.Failure -> {
                    println("Failure  : $it")
                }

            }

        })

    }

}