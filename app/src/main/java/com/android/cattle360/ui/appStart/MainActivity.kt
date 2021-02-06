package com.android.cattle360.ui.appStart

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.android.cattle360.R
import com.android.cattle360.databinding.ActivityMainBinding
import com.android.cattle360.ui.base.BaseActivity
import com.android.cattle360.ui.base.BaseRepository
import com.android.cattle360.ui.user.home.HomeViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getBinding(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        navController = findNavController(R.id.nav_fragment)
        binding.toolbar.setupWithNavController(navController)

        appBarConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.splashFragment,
                    R.id.loginFragment,
                    R.id.passwordFragment,
                    R.id.registrationFragment,
                )
            )
        NavigationUI.setupWithNavController(binding.toolbar, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

//    override fun getFragmentRepository(): BaseRepository {
//        return BaseRepository()
//    }


}