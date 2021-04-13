package com.android.cattle360.ui.user.home.Cattle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
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
        return CattleRepository(remoteDataSource.buildApi(ApiService::class.java))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val livestock_id = arguments?.getString("livestock_id")
        println("passed value .................." + livestock_id)


        viewModel.getCattleData(livestock_id.toString())
        viewModel.cattleResponse.observe(viewLifecycleOwner, Observer {

            val i = Bundle()
            i.putString("livestock_id", livestock_id)
            val frag = ImageSliderFragment()
            frag.arguments = i
            val fragmentManager: FragmentManager? = activity?.supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(R.id.imageSliderContainer,frag)?.commit()

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

                        //ImageSliderFragment.imageiist = it.product_image
                        binding.cattleDataModel = it.value
                        // println("debug ${it.product_image}")


                    } else {
                        println(".............................no data found or error")
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

    }




