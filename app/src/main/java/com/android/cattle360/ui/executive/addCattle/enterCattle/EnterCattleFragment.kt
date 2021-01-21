package com.android.cattle360.ui.executive.addCattle.enterCattle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.databinding.EnterCattleFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import com.android.cattle360.ui.executive.addCattle.AddCattleViewModel

class EnterCattleFragment :
    BaseFragment<AddCattleViewModel, EnterCattleFragmentBinding, AddCattleRepository>() {

    companion object {
        fun newInstance() = EnterCattleFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): EnterCattleFragmentBinding {
        return EnterCattleFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<AddCattleViewModel> {
        return AddCattleViewModel::class.java
    }

    override fun getFragmentRepository(): AddCattleRepository {
        return AddCattleRepository()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.cattlePreviousButton.setOnClickListener {

            requireActivity().onBackPressed()

        }

        binding.cattleNextButton.setOnClickListener {

            NavHostFragment.findNavController(this)
                .navigate(R.id.action_enterCattleFragment_to_cattleImageFragment)

        }

    }

}