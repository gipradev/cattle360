package com.android.cattle360.ui.user.home.Cattle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.databinding.CattleFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.home.Cattle.cattleImageSlider.ImageSliderFragment

class CattleFragment : BaseFragment<CattleViewModel, CattleFragmentBinding, CattleRepository>(){


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
        return CattleRepository()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getCattleData()
        viewModel.cattleResponse.observe(viewLifecycleOwner, Observer {
            inflateImageSlider()
            ImageSliderFragment.imageiist = it.product_image
            binding.cattleDataModel = it.data
            // println("debug ${it.product_image}")

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


    private fun inflateImageSlider() {
        activity?.supportFragmentManager!!.beginTransaction().replace(
            R.id.imageSliderContainer,
            ImageSliderFragment.newInstance(), ImageSliderFragment::class.java.toString()
        ).commit()
    }


}