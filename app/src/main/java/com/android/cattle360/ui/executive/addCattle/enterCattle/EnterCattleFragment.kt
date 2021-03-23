package com.android.cattle360.ui.executive.addCattle.enterCattle

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.apiResponse.DataXXX
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.data.util.toDp
import com.android.cattle360.databinding.EnterCattleFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import com.android.cattle360.ui.executive.addCattle.enterLocation.EnterLocationFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class EnterCattleFragment :
    BaseFragment<EnterCattleViewModel, EnterCattleFragmentBinding, AddCattleRepository>() {
   // var invalid: Boolean = false

    lateinit var pincode_value:String
    lateinit var area_value:String
    lateinit var district_value:String
    lateinit var state_value:String

    lateinit var title_value:String
    lateinit var category_value:String
    lateinit var weight_value:String
    lateinit var age_value:String
    lateinit var color_value:String
    lateinit var biddingamount_value:String
    lateinit var customerprice_value:String

    var  category:String = ""

    companion object {
        fun newInstance() = EnterCattleFragment()

    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): EnterCattleFragmentBinding {
        return EnterCattleFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<EnterCattleViewModel> {
        return EnterCattleViewModel::class.java
    }

    override fun getFragmentRepository(): AddCattleRepository {
        return AddCattleRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        pincode_value =this. arguments?.get("pincode_value").toString()
        area_value = arguments?.getString("area_value").toString()
        district_value = arguments?.getString("district_value").toString()
        state_value = arguments?.getString("state_value").toString()

        title_value = binding.titleEditText.text.toString()
        category_value = binding.categoryEditText.text.toString()
        weight_value = binding.weightEditText.text.toString()
        age_value = binding.ageEditText.text.toString()
        color_value = binding.colorEditText.text.toString()
        biddingamount_value = binding.biddingAmountEditText.text.toString()
        customerprice_value = binding.customerPriceEditText.text.toString()

        println(pincode_value+"................."+area_value+""+district_value+""+state_value)

        categoryLoading()
        addObserver()

        openPopup()
        binding.cattlePreviousButton.setOnClickListener {

            requireActivity().onBackPressed()

        }

        binding.cattleNextButton.setOnClickListener {
           // passValuesWithValidation()

        }

    }

    private fun openPopup() {

        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        builder.setTitle("Is dealer Exist ?")
        val dialogLayout = inflater.inflate(R.layout.is_dealer_exist_dialogbox, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.mobileDialogEditText)
        builder.setView(dialogLayout)

    //    println(pincode_value+","+area_value+","+district_value+","+state_value)

        builder.setPositiveButton("Verify") { _, _ ->

            viewModel.verifyMobile(editText.text.toString())
            viewModel.verifyMobileResponse.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is Resource.Loading -> {
                        println("Loading ")
                    }
                    is Resource.Success -> {
                        if (it.value?.status.equals("1")) {
                            println("Success  : $it")

                            val account_no: String = it.value?.account_no.toString()
                            val address: String = it.value?.address.toString()
                            val bank: String = it.value?.bank.toString()
                            val c_branch: String = it.value?.c_branch.toString()
                            val dealer_name: String = it.value?.dealer_name.toString()
                            val email: String = it.value?.email.toString()
                            val ifsc: String = it.value?.ifsc.toString()
                            val mobile: String = it.value?.mobile.toString()
                            val n_dealer_id: String = it.value?.n_dealer_id.toString()
                            val n_district: String = it.value?.n_district.toString()
                            val n_state: String = it.value?.n_state.toString()

                                    val enterCattleFragment = EnterCattleFragment()
                                    val args = Bundle()
                                    args.putString("pincode_value", pincode_value)
                                    args.putString("area_value", area_value)
                                    args.putString("district_value", district_value)
                                    args.putString("state_value", state_value)
                                    args.putString("title_value", title_value)
                                    args.putString("category_value", category_value)
                                    args.putString("weight_value", weight_value)
                                    args.putString("age_value", age_value)
                                    args.putString("color_value", color_value)
                                    args.putString("biddingamount_value", biddingamount_value)
                                    args.putString("customerprice_value", customerprice_value)

                                    args.putString("account_no", account_no)
                                    args.putString("address", address)
                                    args.putString("bank", bank)
                                    args.putString("c_branch", c_branch)
                                    args.putString("dealer_name", dealer_name)
                                    args.putString("email", email)
                                    args.putString("ifsc", ifsc)
                                    args.putString("mobile", mobile)
                                    args.putString("n_dealer_id", n_dealer_id)
                                    args.putString("n_district", n_district)
                                    args.putString("n_state", n_state)
                                    enterCattleFragment.arguments = args

                                    NavHostFragment.findNavController(this)
                                        .navigate(R.id.action_enterCattleFragment_to_enterDealerFragment,args)


                        }

                    }
                    is Resource.Failure -> {
                        println("Failure  : $it")
                    }

                }
            })

            //    Toast.makeText(requireContext(), "Dealer Number " + editText.text.toString()+" to verify", Toast.LENGTH_SHORT).show() }

            builder.setNeutralButton("Cancel") { _, _ ->

            }

            builder.setNegativeButton("Add new dealer ?") { _, _ ->

                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_enterCattleFragment_to_enterDealerFragment)

            }
            builder.show()
        }

    }

    private fun categoryLoading() = viewModel.category()

    private fun addObserver() {

        viewModel.categoryResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    println("Loading ")
                }
                is Resource.Success -> {
                    if (it.value?.status.equals("1")) {

                        var data: List<DataXXX>? = it.value?.data

                        var stateList = mutableListOf<String>()

                        if (data != null) {
                            for (category in data) {
                                stateList.add(category.c_category_name)
                            }
                            val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, stateList)
                            binding.categoryEditText.setAdapter(arrayAdapter)
                            binding.categoryEditText.threshold = 1

                            binding.categoryEditText.onItemClickListener=AdapterView.OnItemClickListener{
                                    parent, view, position, id ->
                                        category= data[position].c_category_name

                                    }



                        }
                    }
                }
                is Resource.Failure -> {
                    println("Failure  : $it")
                }

            }
        })

    }


fun validationfun(){
    when {
        binding.titleEditText.equals("") -> {
            //invalid = true
            binding.titleEditText.requestFocus()
            binding.titleEditText.error = "Title is required"

        }
        binding.categoryEditText.equals("") -> {
            //  invalid = true
            binding.categoryEditText.requestFocus();
            binding.categoryEditText.error = "Category is required"

        }
        binding.weightEditText.equals("") -> {
            //   invalid = true
            binding.weightEditText.requestFocus();
            binding.weightEditText.error = "Weight is required"

        }
        binding.ageEditText.equals("") -> {
            // invalid = true
            binding.ageEditText.requestFocus();
            binding.ageEditText.error = "Age is required"

        }
        binding.colorEditText.equals("") -> {
            //    invalid = true
            binding.colorEditText.requestFocus();
            binding.colorEditText.error = "Color is required"

        }
        binding.biddingAmountEditText.equals("") -> {
            //     invalid = true
            binding.biddingAmountEditText.requestFocus();
            binding.biddingAmountEditText.error = "Bidding Amount is required"

        }
        binding.customerPriceEditText.equals("") -> {
            // invalid = true
            binding.customerPriceEditText.requestFocus();
            binding.customerPriceEditText.error = "Customer Price is required"

        }
        else -> {
        }}
            //     invalid = false
}



}






