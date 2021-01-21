package com.android.cattle360.ui.user.home.banner

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.android.cattle360.R
import com.android.cattle360.data.demmyModels.BannerModel
import com.android.cattle360.data.demmyModels.bannerSupplier
import com.android.cattle360.data.util.styleViewPager
import com.android.cattle360.databinding.BannerFragmentBinding
import com.android.cattle360.ui.base.BaseFragment

class BannerFragment : BaseFragment<BannerViewModel, BannerFragmentBinding, BannerRepository>() {

    private val bannerAdapter: BannerAdaptor by lazy { BannerAdaptor() }
    var itemCount: Int = bannerSupplier.bannerItem.size

    companion object {
        fun newInstance() = BannerFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BannerFragmentBinding {
        return BannerFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<BannerViewModel> = BannerViewModel::class.java

    override fun getFragmentRepository(): BannerRepository {
        return BannerRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.bannerViewPager.adapter = bannerAdapter

        viewModel.getBannerList()
        viewModel.bannerResponse.observe(viewLifecycleOwner, Observer {
            bannerAdapter.list = it
        })


        //Slider call back method
        binding.bannerViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //setCurrentInicator(position)
                Handler(Looper.getMainLooper()).removeCallbacks(runnable)
                Handler(Looper.getMainLooper()).postDelayed(runnable, 10000L)
            }
        })
       // setUpIndicator(bannerSupplier.bannerItem)

        binding.indicator.setViewPager(binding.bannerViewPager)
        bannerAdapter.registerAdapterDataObserver(binding.indicator.getAdapterDataObserver());

        binding.bannerViewPager.styleViewPager(binding.bannerViewPager)

    }


    val runnable: Runnable = object : Runnable {
        override fun run() {
            if (binding.bannerViewPager.currentItem < itemCount - 1) {
                binding.bannerViewPager.setCurrentItem(binding.bannerViewPager.currentItem + 1)
            } else binding.bannerViewPager.setCurrentItem(0)

        }
    }

    //setUpPageIndicator
    private fun setUpIndicator(bannerItem: List<BannerModel>) {
        val indicators = arrayOfNulls<ImageView>(bannerItem.size)
        val layoutParms: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParms.setMargins(8, 0, 8, 0)

        for (i in indicators.indices) {
            indicators[i] = ImageView(requireContext())
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.default_pager_indicator)
                )
                this?.layoutParams = layoutParms
            }
            binding.indicatorContainer.addView(indicators[i])
        }
    }


    //setCurrentIndicator
    private fun setCurrentInicator(index: Int) {
        val childCount = binding.indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorContainer[i] as ImageView
            if (i == index) {

                imageView.load(R.drawable.selected_pager_indicator)
//                imageView.setImageDrawable(
//                    ContextCompat.getDrawable(requireContext(), R.drawable.selected_pager_indicator)
//                )

            } else {
                imageView.load(R.drawable.default_pager_indicator)
//                imageView.setImageDrawable(
//                    ContextCompat.getDrawable(requireContext(), R.drawable.default_pager_indicator)
//                )

            }
        }
    }

}


//        TabLayoutMediator(binding.indicator, binding.bannerViewPager) { tab, position -> }.attach()
//
//        val compositePageTransformer = CompositePageTransformer()
//        compositePageTransformer.addTransformer(MarginPageTransformer(10))
//        compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
//            var r = 1 - Math.abs(position)
//            page.scaleY = 0.85f + r * 0.15f
//        })
//        binding.bannerViewPager.setPageTransformer(compositePageTransformer)