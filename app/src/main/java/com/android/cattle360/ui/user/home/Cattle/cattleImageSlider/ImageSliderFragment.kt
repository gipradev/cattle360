package com.android.cattle360.ui.user.home.Cattle.cattleImageSlider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android.cattle360.data.apiResponse.DataLivestockImageList
import com.android.cattle360.data.demmyModels.CattleImageModel
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.ImageSliderFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.home.Cattle.CattleImageAdaptor
import com.android.cattle360.ui.user.home.Cattle.CattleRepository

class ImageSliderFragment :
    BaseFragment<ImageSliderViewModel, ImageSliderFragmentBinding, CattleRepository>() {

    private val imageSliderAdapter: CattleImageAdaptor by lazy { CattleImageAdaptor() }

    companion object {
        fun newInstance() = ImageSliderFragment()
        var imageiist: List<DataLivestockImageList> = arrayListOf()
            set(value) {
                field = value
            }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ImageSliderFragmentBinding {
        return ImageSliderFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<ImageSliderViewModel> {
        return ImageSliderViewModel::class.java
    }

    override fun getFragmentRepository(): CattleRepository {
        return CattleRepository(remoteDataSource.buildApi(ApiService::class.java))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var livestock_id = arguments?.getString("livestock_id").toString()
println("..............shhdg.......................................................$livestock_id")

        viewModel.getCattleImageData(livestock_id)
        viewModel.cattleImageResponse.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {

        if (it.value?.status.equals("1")) {

        imageiist = it.value?.data!!
        binding.imageViewPager.adapter = imageSliderAdapter
       //imageSliderAdapter.list = imageiist
        imageSliderAdapter.list = imageiist
        binding.indicator.setViewPager(binding.imageViewPager)
        imageSliderAdapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver);


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

}