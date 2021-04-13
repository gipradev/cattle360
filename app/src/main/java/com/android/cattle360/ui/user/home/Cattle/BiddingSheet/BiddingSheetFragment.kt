package com.android.cattle360.ui.user.home.Cattle.BiddingSheet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.databinding.BiddingSheetFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar


class BiddingSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BiddingSheetFragmentBinding
    private lateinit var viewModel: BiddingSheetViewModel

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
        viewModel = ViewModelProvider(this).get(BiddingSheetViewModel::class.java)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.seekText.setText("10000")
        binding.seekBar.max = 20000
        binding.seekBar.progress = 10000

        binding.seekBar.setOnProgressChangeListener(object :
            DiscreteSeekBar.OnProgressChangeListener {
            override fun onProgressChanged(
                seekBar: DiscreteSeekBar?,
                value: Int,
                fromUser: Boolean
            ) = binding.seekText.setText("$value")

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

            NavHostFragment.findNavController(this)
                .navigate(R.id.action_biddingSheetFragment_to_cattleCartFragment)
        }


    }

    interface OnClickEvent {
        fun onAddButton()
        fun onCheckOutButton()
    }


}


