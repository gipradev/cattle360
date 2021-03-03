package com.android.cattle360.ui.executive.addCattle.enterDealerDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.databinding.EnterDealerFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository


class EnterDealerFragment :
    BaseFragment<EnterDealerViewModel, EnterDealerFragmentBinding, AddCattleRepository>() {
    var invalid: Boolean = false

    companion object {
        fun newInstance() = EnterDealerFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): EnterDealerFragmentBinding {
        return EnterDealerFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<EnterDealerViewModel> {
        return EnterDealerViewModel::class.java
    }

    override fun getFragmentRepository(): AddCattleRepository {
        return AddCattleRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.cattlePreviousButton.setOnClickListener {

            requireActivity().onBackPressed()

        }

        binding.cattleNextButton.setOnClickListener {
            //passValuesWithValidation()
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_enterDealerFragment_to_cattleImageFragment)
        }


    }

        }










