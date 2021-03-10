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
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
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
    var invalid: Boolean = false

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

        binding.cattlePreviousButton.setOnClickListener {

            requireActivity().onBackPressed()

        }

        binding.cattleNextButton.setOnClickListener {
           // passValuesWithValidation()
            openPopup()
        }

    }

    private fun openPopup() {

        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        builder.setTitle("Is dealer Exist ?")
        val dialogLayout = inflater.inflate(R.layout.is_dealer_exist_dialogbox, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.mobileDialogEditText)
        builder.setView(dialogLayout)

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
                                .navigate(R.id.action_enterCattleFragment_to_enterDealerFragment)

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


    }}






