package com.android.cattle360.ui.user.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.databinding.HomeFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.home.banner.BannerFragment
import com.android.cattle360.ui.user.home.category.CategoryFragment
import com.android.cattle360.ui.user.home.liveStock.LiveStockFragment
import com.android.cattle360.ui.user.home.location.LocationFragment

class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentBinding, HomeRepository>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HomeFragmentBinding {
        return HomeFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getFragmentRepository(): HomeRepository {
        return HomeRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        launchLocationFragment()
        launchBannerFragment()
        launchCategoryFragment()
        launchLiveStockFragment()

    }

    private fun launchLiveStockFragment() {

        activity?.supportFragmentManager!!.beginTransaction().replace(
            R.id.live_stock_nav_fragment,
            LiveStockFragment.newInstance(), LiveStockFragment::class.java.toString()
        ).commit()

    }

    private fun launchCategoryFragment() {
        activity?.supportFragmentManager!!.beginTransaction().replace(
            R.id.category_nav_fragment,
            CategoryFragment.newInstance(), CategoryFragment::class.java.toString()
        ).commit()
    }
//to do in future
    private fun launchBannerFragment() {
        activity?.supportFragmentManager!!.beginTransaction().replace(
            R.id.banner_nav_fragment,
            BannerFragment.newInstance(), BannerFragment::class.java.toString()
        ).commit()
    }

    private fun launchLocationFragment() {
        activity?.supportFragmentManager!!.beginTransaction().replace(
            R.id.location_nav_fragment,
            LocationFragment.newInstance(), LocationFragment::class.java.toString()
        ).commit()
    }

}