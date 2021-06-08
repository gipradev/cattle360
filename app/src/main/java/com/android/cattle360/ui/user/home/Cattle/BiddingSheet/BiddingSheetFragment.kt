 package com.android.cattle360.ui.user.home.Cattle.BiddingSheet

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.RemoteDataSource
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.BiddingSheetFragmentBinding
import com.android.cattle360.ui.base.ViewModelFactory
import com.android.cattle360.ui.user.home.Cattle.CattleRepository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar
import java.text.SimpleDateFormat
import java.util.*


 class BiddingSheetFragment : BottomSheetDialogFragment() {
    private val TAG = "BiddingSheetFragment"
     private lateinit var countDownTimer: CountDownTimer
    private lateinit var binding: BiddingSheetFragmentBinding
    private lateinit var viewModel: BiddingSheetViewModel
  private lateinit var remoteDataSource:RemoteDataSource

     companion object {
        fun newInstance() = BiddingSheetFragment()
    }

     override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.bidding_sheet_fragment,
            container,
            false
        )
        println(".............................viewmodel")
        val factory = ViewModelFactory(CattleRepository())
        viewModel = ViewModelProvider(this, factory).get(BiddingSheetViewModel::class.java)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        startTimer()

        binding.seekText.setText("10000")
        binding.seekBar.max = 100000
        binding.seekBar.progress = 10000
// var value=binding.seekText.setText("10000")
         var progressChangedValue:Int = 0
        binding.seekBar.setOnProgressChangeListener(object :
            DiscreteSeekBar.OnProgressChangeListener {
            override fun onProgressChanged(
                seekBar: DiscreteSeekBar?,
                value: Int,
                fromUser: Boolean
            ) {
                progressChangedValue=value
                binding.seekText.setText("$value")
            }

            override fun onStartTrackingTouch(seekBar: DiscreteSeekBar?) {}
            override fun onStopTrackingTouch(seekBar: DiscreteSeekBar?) {}
        })


        binding.seekText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!text.isNullOrEmpty() && !text.isNullOrBlank())
                    binding.seekBar.progress = Integer.parseInt(text.trim().toString())
                else
                    binding.seekBar.progress = 0
            }

            override fun afterTextChanged(p0: Editable?) {
                // binding.seekText.text?.clearSpans()
                binding.seekText.setSelection(p0.toString().length)

            }

        })

        binding.addButton.setOnClickListener {

        }
        binding.checkOutButton.setOnClickListener {


            viewModel.getBid("200", "49", "4")
            viewModel.getbidamountResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

                when (it) {
                    is Resource.Loading -> {
                        println("Loading")
                    }
                    is Resource.Success -> {

                        if (it.value?.status.equals("1")) {
                            //    binding.cattleDataModel = it.value
                            startTimer()
                            Snackbar.make(
                                requireView(),
                                "${it.value?.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                            NavHostFragment.findNavController(this)
                                .navigate(R.id.action_biddingSheetFragment_to_cattleCartFragment)
                        } else {
                            Toast.makeText(
                               requireContext(),
                                "${it.value?.message},d_date_time=${it.value?.d_date_time}",
                                Toast.LENGTH_LONG
                            ).show()
                            println("..........................else msg..${it.value?.message}.")
                        }
                    }

                    is Resource.Failure -> {
                        println("Failure  : $it")
                    }

                }

            })



        }


    }
    private fun startTimer()
    {
        //val currentTime = Calendar.getInstance().time
        val startDateDay = "2021-05-31 15:30:00"
        val endDateDay = "2021-05-31 16:00:00"
        val format1 = SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault())
        val endDate = format1.parse(endDateDay)
        val startDate = format1.parse(startDateDay)
        //milliseconds
        var different =  endDate.time - startDate.time
        println("diffrnt........................................." + different)
        countDownTimer = object : CountDownTimer(different, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                var diff = millisUntilFinished
                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60
                val hoursInMilli = minutesInMilli * 60
                val daysInMilli = hoursInMilli * 24

                val elapsedDays = diff / daysInMilli
                diff %= daysInMilli

                val elapsedHours = diff / hoursInMilli
                diff %= hoursInMilli

                val elapsedMinutes = diff / minutesInMilli
                diff %= minutesInMilli

                val elapsedSeconds = diff / secondsInMilli
               // binding.textViewTimer.text = "$elapsedDays days $elapsedHours hs $elapsedMinutes min $elapsedSeconds sec"
                binding.textViewTimer.text = "$elapsedMinutes min $elapsedSeconds sec  Left"
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.textViewTimer.setTextColor(Color.RED)
                binding.textViewTimer.text = "Bid time out!"
            }
        }.start()

    }
    interface OnClickEvent {
        fun onAddButton()
        fun onCheckOutButton()
    }


}



