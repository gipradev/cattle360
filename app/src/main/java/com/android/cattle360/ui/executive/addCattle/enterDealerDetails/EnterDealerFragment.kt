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

        val account_no =arguments?.getString("account_no")
        val address = arguments?.getString("address")
        val bank =arguments?.getString("bank")
        val c_branch =arguments?.getString("c_branch")
        val dealer_name = arguments?.getString("dealer_name")
        val email =arguments?.getString("email")
        val ifsc = arguments?.getString("ifsc")
        val mobile = arguments?.getString("mobile")
        val n_dealer_id =arguments?.getString("n_dealer_id")
        val n_district = arguments?.getString("n_district")
        val n_state = arguments?.getString("n_state")

        binding.accountNumberEditText.setText(account_no)
        binding.addressEditText.setText(address)
        binding.bankNameEditText.setText(bank)
        binding.branchNameEditText.setText(c_branch)
        binding.nameEditText.setText(dealer_name)
        binding.emailEditText.setText(email)
        binding.ifscNumberEditText.setText(ifsc)
        binding.mobileEditText.setText(mobile)
       // binding.d.setText(n_dealer_id)
        //binding.dis.setText(n_district)
       // binding.accountNumberEditText.setText(n_state)

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










