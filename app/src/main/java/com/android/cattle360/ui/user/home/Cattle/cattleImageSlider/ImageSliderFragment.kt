package com.android.cattle360.ui.user.home.Cattle.cattleImageSlider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.data.demmyModels.CattleImageModel
import com.android.cattle360.databinding.ImageSliderFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.home.Cattle.CattleImageAdaptor
import com.android.cattle360.ui.user.home.Cattle.CattleRepository

class ImageSliderFragment :
    BaseFragment<ImageSliderViewModel, ImageSliderFragmentBinding, CattleRepository>() {

    private val imageSliderAdapter: CattleImageAdaptor by lazy { CattleImageAdaptor() }

    companion object {
        fun newInstance() = ImageSliderFragment()
        var imageiist: List<CattleImageModel> = arrayListOf()
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
        return CattleRepository()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.imageViewPager.adapter = imageSliderAdapter
        imageSliderAdapter.list = imageiist
        binding.indicator.setViewPager(binding.imageViewPager)
        imageSliderAdapter.registerAdapterDataObserver(binding.indicator.getAdapterDataObserver());
    }

}