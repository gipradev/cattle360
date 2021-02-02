package com.android.cattle360.ui.executive

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.android.cattle360.R
import com.android.cattle360.databinding.ActivityExecutiveBinding
import com.android.cattle360.ui.base.BaseActivity
import com.android.cattle360.ui.executive.addCattle.AddCattleActivity

class ExecutiveActivity : BaseActivity<ExecutiveActivityViewModel, ActivityExecutiveBinding>() {

    private lateinit var navController: NavController

    override fun getViewModel(): Class<ExecutiveActivityViewModel> =
        ExecutiveActivityViewModel::class.java

    override fun getBinding(): Int = R.layout.activity_executive

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomNavBar.background = null
        binding.bottomNavBar.menu.getItem(1).isEnabled = false

        navController = findNavController(R.id.executive_nav_fragment)
        binding.bottomNavBar.setupWithNavController(navController)
        NavigationUI.setupWithNavController(binding.bottomNavBar, navController)

    }

    fun addNewCattle(view: View) {

        val intent = Intent(applicationContext, AddCattleActivity::class.java)
        startActivity(intent)
    }

}