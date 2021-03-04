package com.android.cattle360.ui.executive.addCattle.uploadImage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.CattleImageFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import com.android.cattle360.ui.executive.addCattle.uploadImage.uploadFragment.UploadFragment
import com.google.android.material.snackbar.Snackbar

class CattleImageFragment :
    BaseFragment<CattleImageViewModel, CattleImageFragmentBinding, AddCattleRepository>() {

    companion object {
        fun newInstance() = CattleImageFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CattleImageFragmentBinding {
        return CattleImageFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<CattleImageViewModel> {
        return CattleImageViewModel::class.java
    }

    override fun getFragmentRepository(): AddCattleRepository {
        return AddCattleRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        launchUploadFragment()

        binding.imagePreviousButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.imageNextButton.setOnClickListener {
          insertAllCattleDetails()
        }
    }

    private fun insertAllCattleDetails() {

        val pincode_value =arguments?.getString("pincode_value")
        val area_value = arguments?.getString("area_value")
        val district_value =arguments?.getString("district_value")
        val state_value = arguments?.getString("state_value")

        val title_value=arguments?.getString("title_value")
        val category_value= arguments?.getString("category_value")
        val weight_value= arguments?.getString("weight_value")
        val age_value= arguments?.getString("age_value")
        val color_value=arguments?.getString("color_value")
        val biddingamount_value= arguments?.getString("biddingamount_value")
        val customerprice_value= arguments?.getString("customerprice_value")

//println(pincode_value.toString()+",,,"+area_value.toString()+",,,"+district_value.toString()+",,,"+state_value.toString()+",,,"+title_value.toString()
//        +",,,"+category_value.toString()+",,,"+weight_value.toString()+",,,"+age_value.toString()+",,,"+color_value.toString()+",,,"+biddingamount_value.toString()+",,,"+customerprice_value.toString())
//        viewModel.insertCattleDetails(pincode_value.toString(),area_value.toString(),district_value.toString(),state_value.toString(),title_value.toString()
//            ,category_value.toString(),weight_value.toString(),age_value.toString(),color_value.toString(),biddingamount_value.toString(),customerprice_value.toString())

//        viewModel.insertCattleDetailsResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
//            //  println("sssssssssssssssssssssss ${it}")
//
//            when (it) {
//                is Resource.Loading -> {
//                    println("Loading ${it}")
//                }
//                is Resource.Success -> {
//                    if (it.value?.status.equals("1")) {
//                        println("Success 1 : ${it}")
//                        Snackbar.make(
//                            requireView(),
//                            "${it.value?.message}",
//                            Snackbar.LENGTH_LONG
//                        ).show()
//                    } else if (it.value?.status.equals("0")){
//                        println("else if 0  : ${it}")
//
//                        Snackbar.make(
//                            requireView(),
//                            "${it.value?.message}",
//                            Snackbar.LENGTH_LONG
//                        ).show()
//                    }
//                }
//                is Resource.Failure -> {
//                    println("Failure  : ${it}")
//                }
//
//            }
//        })
    }

    private fun launchUploadFragment() {
        activity?.supportFragmentManager!!.beginTransaction().replace(
            R.id.uploadFragment,
            UploadFragment.newInstance(), UploadFragment::class.java.toString()
        ).commit()

    }

}