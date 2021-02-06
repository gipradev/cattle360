package com.android.cattle360.ui.user

import android.content.res.Configuration
import android.os.Bundle
import android.view.Window
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.util.changeStatusBarColor
import com.android.cattle360.data.util.visibility
import com.android.cattle360.databinding.ActivityHomeBinding
import com.android.cattle360.ui.base.BaseActivity
import com.android.cattle360.ui.base.BaseRepository
import com.android.cattle360.ui.user.home.HomeRepository
import com.android.cattle360.ui.user.home.HomeViewModel


class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun getViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getBinding(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.homeToolbar.title = ""
        setSupportActionBar(binding.homeToolbar)

        navController = findNavController(R.id.home_nav_host)
        binding.bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph)

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
        NavigationUI.setupWithNavController(binding.homeToolbar, navController, appBarConfiguration)


        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val window = window
            val mode = resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)
            when (destination.id) {
                R.id.searchLocationFragment -> {
                    binding.bottomNavigationView.visibility(false)
                    binding.homeToolbar.visibility(true)
                }
                R.id.addressListFragment -> {
                    binding.homeToolbar.visibility(true)
                    binding.bottomNavigationView.visibility(false)
                }
                R.id.changePasswordFragment -> {
                    binding.homeToolbar.visibility(true)
                    binding.bottomNavigationView.visibility(false)
                }
                R.id.cattleFragment -> {
                    binding.homeToolbar.visibility(true)
                    binding.bottomNavigationView.visibility(false)
                }
                R.id.walletFragment -> {
                    binding.homeToolbar.visibility(true)
                    binding.bottomNavigationView.visibility(false)

                }
                R.id.biddingSheetFragment -> {
                    binding.bottomNavigationView.visibility(false)
                }
                R.id.biddingFragment -> {

                    changeStatusBarToGreen(mode, window)
                    binding.bottomNavigationView.visibility(true)
                    binding.homeToolbar.visibility(false)
                }

                R.id.categoryListFragment -> {
                    changeStatusBarToGreen(mode, window)
                    binding.bottomNavigationView.visibility(false)
                    binding.homeToolbar.visibility(false)
                }
                R.id.cattleCartFragment ->{
                    binding.bottomNavigationView.visibility(false)
                    binding.homeToolbar.visibility(true)
                }

                R.id.orderFragment -> {
                    changeStatusBarToGreen(mode, window)
                    binding.bottomNavigationView.visibility(true)
                    binding.homeToolbar.visibility(false)
                }

                else -> {
                    changeStatusBarToWhite(mode, window)
                    binding.bottomNavigationView.visibility(true)
                    binding.homeToolbar.visibility(false)
                }
            }
        }


        binding.bottomNavigationView.setOnNavigationItemReselectedListener {
            when (it.itemId) {

            }
        }


    }

    private fun changeStatusBarToGreen(mode: Int?, window: Window) {
        when (mode) {
            Configuration.UI_MODE_NIGHT_YES -> changeStatusBarColor(window, 174, 217, 169)
            Configuration.UI_MODE_NIGHT_NO -> changeStatusBarColor(window, 65, 130, 53)
        }
    }

    private fun changeStatusBarToWhite(mode: Int?, window: Window) {
        when (mode) {
            Configuration.UI_MODE_NIGHT_YES -> changeStatusBarColor(window, 18, 18, 18)
            Configuration.UI_MODE_NIGHT_NO -> changeStatusBarColor(window, 255, 255, 255)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

//    override fun getFragmentRepository(): BaseRepository {
//
//        return HomeRepository(remoteDataSource.buildApi(ApiService::class.java))
//    }


}