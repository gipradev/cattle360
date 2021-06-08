 package com.android.cattle360.ui.user.home.Cattle

import android.R.attr
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.CattleFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.home.Cattle.cattleImageSlider.ImageSliderFragment
import java.text.SimpleDateFormat
import java.util.*


class CattleFragment : BaseFragment<CattleViewModel, CattleFragmentBinding, CattleRepository>(){

    private lateinit var countDownTimer: CountDownTimer
    companion object {
        fun newInstance() = CattleFragment()
   }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CattleFragmentBinding {
        return CattleFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<CattleViewModel> {
        return CattleViewModel::class.java
    }

    override fun getFragmentRepository(): CattleRepository {
        return CattleRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        val dayInMilli = (60 * 60 * 24 * 1000).toLong()
//        binding.viewTimer.setBase(SystemClock.elapsedRealtime() - dayInMilli)

//        if (binding.viewTimer.text==String.format("%d:%d", 0, 0)){
//            binding.viewTimer.setTextColor(Color.RED)
    ///       binding.viewTimer.isCountDown = false
//            binding.viewTimer.stop()
//    }

        val livestock_id = arguments?.getString("livestock_id")
        println("passed value ........livestkid.........." + livestock_id)

        viewModel.getCattleData(livestock_id.toString())
        viewModel.cattleResponse.observe(viewLifecycleOwner, Observer {

            val i = Bundle()
            i.putString("livestock_id", livestock_id)
            val frag = ImageSliderFragment()
            frag.arguments = i
            val fragmentManager: FragmentManager? = activity?.supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.imageSliderContainer, frag)?.commit()
            //                        activity?.supportFragmentManager!!.beginTransaction().replace(
//                            R.id.imageSliderContainer,
//                            ImageSliderFragment.newInstance(),
//                            ImageSliderFragment::class.java.toString(),
//                        ).commit()

            when (it) {
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {

                    if (it.value?.status.equals("1")) {

                       //ImageSliderFragment.imageiist = it
                        binding.cattleDataModel = it.value
                        startTimer()
                    } else {
                        println(".............................nodata")
                    }
                }

                is Resource.Failure -> {
                    println("Failure  : $it")
                }

            }

        })

        binding.startBiddingButton.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_cattleFragment_to_biddingSheetFragment)

//            val modalBottomSheet = BiddingSheetFragment.newInstance()
//            activity?.supportFragmentManager?.let { it ->
//                modalBottomSheet.show(
//                    it,
//                    BiddingSheetFragment.TAG
//                )
//            }

        }
    }

    private fun startTimer() {

        val startDateDay = "2021-05-31 19:40:00"
        val endDateDay = "2021-05-31 19:45:00"
        val format1 = SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault())
        val endDate = format1.parse(endDateDay)
        val startDate = format1.parse(startDateDay)
        //milliseconds
        var different =  endDate.time - startDate.time
        val numberOfSeconds: Int = (different / 1000).toInt() // Ex : 20000/1000 = 20

        val factor = 100 / numberOfSeconds
        countDownTimer = object : CountDownTimer(different, 1000){
            // 500 means, onTick function will be called at every 500 milliseconds
            @SuppressLint("SetTextI18n")
            override fun onTick(leftTimeInMilliseconds: Long) {
                var diff = leftTimeInMilliseconds
                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60

                val elapsedMinutes = diff / minutesInMilli
                diff %= minutesInMilli

                val elapsedSeconds = diff / secondsInMilli
                val seconds = leftTimeInMilliseconds / 1000
                //incrementing progress on every tick
                binding.barTimer.progress=seconds.toInt()
                binding.textViewTimerviewTime.text = String.format("%02d", elapsedMinutes) + ":" + String.format(
                    "%02d",
                    elapsedSeconds
                )
                // format the textview to show the easily readable format
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                if (binding.textViewTimerviewTime.text.equals("00:00")) {
                    binding.textViewTimerviewTime.setTextColor(Color.RED)
                    binding.textViewTimerviewTime.text = "Time Out!"
                }
            }
        }.start()
    }


}




