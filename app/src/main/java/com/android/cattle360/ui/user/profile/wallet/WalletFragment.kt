package com.android.cattle360.ui.user.profile.wallet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.WalletFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultListener
import org.json.JSONObject


class WalletFragment : BaseFragment<WalletViewModel, WalletFragmentBinding, WalletRepository>(), PaymentResultListener {

    private val TAG = "WalletFragment"
    private val progressBar: ProgressBar? = null
    private val midString = "Your production mode MID here"
    private  var txnAmountString:String? = ""
    private  var orderIdString:String? = ""
    private  var txnTokenString:String? = ""
    private val ActivityRequestCode = 2


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


      binding.addMoneyButton.setOnClickListener(View.OnClickListener {
          txnAmountString = binding.enterAmount.text.toString()
          println("money..........." + binding.enterAmount.text.toString())

          if (txnAmountString.equals("")) {
              Toast.makeText(requireActivity(), "Amount is empty", Toast.LENGTH_LONG).show();
          } else {
              startPayment();
          }
          var errors = ""
//          if (orderIdString.equals("")) {
//              errors = "Enter valid Order ID here\n"
//              Toast.makeText(requireActivity(), errors, Toast.LENGTH_SHORT).show()
//          } else if (txnAmountString.equals("")) {
//              errors = "Enter valid Amount here\n"
//              Toast.makeText(requireActivity(), errors, Toast.LENGTH_SHORT).show()
//          } else {
//              getToken()
//          }
      })



        viewModel.getWalletData()
        viewModel.walletResponse.observe(viewLifecycleOwner, Observer {
            binding.walletModel = it
            binding.transactionRecycler.adapter = transactionAdapter
            transactionAdapter.list = it.transaction
        })
    }
    private fun startPayment() {
        /**
         * You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        /**
         * You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        val co = Checkout()
        try {
            val options = JSONObject()
            options.put("name", "Cattle360 App")
            options.put("description", "App Payment")
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://www.mandee.store/cattle360/assets/frontarea//images/logo.png")
            options.put("currency", "INR")
            val payment: String = txnAmountString.toString()
            println("payyyyyyyyyyy" + payment)
            // amount is in paise so please multiple it by 100
            //Payment failed Invalid amount (should be passed in integer paise. Minimum value is 100 paise, i.e. ₹ 1)
            var total = payment.toDouble()
            total = total * 100
            options.put("amount", total)
//            val preFill = JSONObject()
//            preFill.put("email", "cattle360.2021@gmail.com")
//            preFill.put("contact", "9072990515")
//            options.put("prefill", preFill)


            co.open(requireActivity(), options)
        } catch (e: Exception) {
            Toast.makeText(requireActivity(), "Error in payment: " + e.message, Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

//    private fun getToken() {
//
////        Test Merchant ID
////        VECeCU35261452240033
////        Test Merchant Key
////        sJl2cIjm@gGmFyK0
////        Website
////        WEBSTAGING
////        Industry Type
////                Retail
////        Channel ID (For Website)
////        WEB
////        Channel ID (For Mobile Apps)
////        WAP
////
//
//        Log.e(TAG, " get token start");
//        progressBar?.visibility = View.VISIBLE;
//        viewModel.getTokenfun("12345", "VECeCU35261452240033", "order123", "1.00")
//
//        viewModel.getTokenResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
//            //  println("sssssssssssssssssssssss ${it}")
//            when (it) {
//                is Resource.Loading -> {
//                    println("Loading ${it}")
//                }
//                is Resource.Success -> {
//                    println("Success 1 : ${it}")
//                    println("Success 1 messge : ${it.value?.body}")
//
//                }
//                is Resource.Failure -> {
//                    println("Failure  : ${it}")
//                }
//
//            }
//        })
//
//    }

    override fun onPaymentSuccess(p0: String?) {
        Log.e(TAG, " payment successfull " + p0.toString());
        Toast.makeText(requireActivity(), "Payment successfully done! " + p0, Toast.LENGTH_LONG).show();
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Log.e(
            TAG,
            "error code " + java.lang.String.valueOf(p0) + " -- Payment failed " + p1.toString()
        )
        try {
            Toast.makeText(requireActivity(), "Payment error please try again", Toast.LENGTH_LONG).show()
        } catch (e: java.lang.Exception) {
            Log.e("OnPaymentError", "Exception in onPaymentError", e)
        }
    }

}



