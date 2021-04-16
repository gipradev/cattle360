  package com.android.cattle360.ui.executive.addCattle.enterDealerDetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.EnterDealerFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import com.android.cattle360.ui.executive.addCattle.enterCattle.EnterCattleFragment
import com.google.android.material.snackbar.Snackbar


  class EnterDealerFragment :
    BaseFragment<EnterDealerViewModel, EnterDealerFragmentBinding, AddCattleRepository>() {
    var invalid: Boolean = false
      val args=Bundle()

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
        val pincode_value =arguments?.getString("pincode_value")
        val area_value = arguments?.getString("area_value")
        val district_value =arguments?.getString("district_value")
        val state_value = arguments?.getString("state_value")

        val title_value=arguments?.getString("title_value")
        val category_value= arguments?.getString("category_value")
        val weight_value= arguments?.getString("weight_value")
        val age_value= arguments?.getString("age_value")
        val color_value=arguments?.getString("color_value")
        val biddingamount_value= arguments?.getString("biddingamount_value")
        val customerprice_value= arguments?.getString("customerprice_value")

        val dealer_account_no =arguments?.getString("account_no")
        val  dealer_address = arguments?.getString("address")
        val  dealer_bank =arguments?.getString("bank")
        val  dealer_c_branch =arguments?.getString("c_branch")
        val dealer_name = arguments?.getString("dealer_name")
        val  dealer_email =arguments?.getString("email")
        val  dealer_ifsc = arguments?.getString("ifsc")
        val  dealer_mobile = arguments?.getString("mobile")
        val n_dealer_id =arguments?.getString("n_dealer_id")
        val n_district = arguments?.getString("n_district")
        val n_state = arguments?.getString("n_state")

        binding.accountNumberEditText.setText(dealer_account_no)
        binding.addressEditText.setText(dealer_address)
        binding.bankNameEditText.setText(dealer_bank)
        binding.branchNameEditText.setText(dealer_c_branch)
        binding.nameEditText.setText(dealer_name)
        binding.emailEditText.setText(dealer_email)
        binding.ifscNumberEditText.setText(dealer_ifsc)
        binding.mobileEditText.setText(dealer_mobile)
       // binding.de.setText(n_dealer_id)
        //binding.dis.setText(n_district)
       // binding.accountNumberEditText.setText(n_state)

        println(".....................dealer frag....................."+pincode_value.toString()
                +",,,"+area_value.toString()+",,,"+district_value.toString()+",,,"
                +state_value.toString()+",,,"+title_value.toString()
                +",,,"+category_value.toString()+",,,"+weight_value.toString()
                +",,,"+age_value.toString()+",,,"+color_value.toString()+",,,"
                +biddingamount_value.toString()+",,,"+customerprice_value.toString())


        binding.cattlePreviousButton.setOnClickListener {

            requireActivity().onBackPressed()

        }

        binding.cattleNextButton.setOnClickListener {
            //passValuesWithValidation()

            viewModel.insertCattleDetails(pincode_value.toString(),area_value.toString(),district_value.toString(),state_value.toString(),title_value.toString()
                ,category_value.toString(),weight_value.toString(),age_value.toString(),color_value.toString(),biddingamount_value.toString(),customerprice_value.toString(),
                n_dealer_id.toString(),binding.accountNumberEditText.text.toString(),binding.addressEditText.text.toString(),binding.bankNameEditText.text.toString()
                ,binding.branchNameEditText.text.toString(), binding.nameEditText.text.toString(),binding.emailEditText.text.toString(),
                binding.ifscNumberEditText.text.toString(),binding.mobileEditText.text.toString())

            viewModel.insertCattleDetailsResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                //  println("sssssssssssssssssssssss ${it}")

                when (it) {
                    is Resource.Loading -> {
                        println("Loading ${it}")
                    }
                    is Resource.Success -> {
                        if (it.value?.status.equals("1")) {
                            println("Success 1 : ${it}")
                            var livestock_id=it.value?.livestock_id.toString()
                            val enterDealerFragment = EnterDealerFragment()
                            //args = Bundle()
                            println(".........................live............................................"+livestock_id)
                            args.putString("livestock_id", livestock_id)
                            enterDealerFragment.arguments = args

                            val sharedPreference = requireContext().getSharedPreferences(
                                "pref", Context.MODE_PRIVATE)
                            val editor = sharedPreference.edit()
                            editor.putString("livestock_id", it.value?.livestock_id.toString())
                            println("livestock id...${it.value?.livestock_id}")
                            editor.apply()
                            editor.commit()

                            Snackbar.make(
                                requireView(),
                                "${it.value?.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                        } else if (it.value?.status.equals("0")){
                            println("else if 0  : ${it}")

                            Snackbar.make(
                                requireView(),
                                "${it.value?.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                    is Resource.Failure -> {
                        println("Failure  : ${it}")
                    }

                }
            })


            NavHostFragment.findNavController(this)
                .navigate(R.id.action_enterDealerFragment_to_cattleImageFragment,args)
        }


    }





}










