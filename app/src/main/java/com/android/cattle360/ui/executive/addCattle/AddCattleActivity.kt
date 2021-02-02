package com.android.cattle360.ui.executive.addCattle

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.android.cattle360.R
import com.android.cattle360.databinding.ActivityAddCattleBinding
import com.android.cattle360.ui.base.BaseActivity

class  AddCattleActivity : BaseActivity<AddCattleViewModel, ActivityAddCattleBinding>() {

    private lateinit var navController: NavController

    override fun getViewModel(): Class<AddCattleViewModel> = AddCattleViewModel::class.java

    override fun getBinding(): Int = R.layout.activity_add_cattle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(R.id.add_cattle_nav_fragment)

    }
}