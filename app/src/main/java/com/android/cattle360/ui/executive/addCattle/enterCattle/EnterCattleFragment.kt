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
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.util.toDp
import com.android.cattle360.databinding.EnterCattleFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import com.android.cattle360.ui.executive.addCattle.enterLocation.EnterLocationFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        val editText  = dialogLayout.findViewById<EditText>(R.id.mobileDialogEditText)
        builder.setView(dialogLayout)

        builder.setPositiveButton("Verify") { _, _ ->

            NavHostFragment.findNavController(this)
                .navigate(R.id. action_enterCattleFragment_to_enterDealerFragment)

            Toast.makeText(requireContext(), "Dealer Number " + editText.text.toString()+" to verify", Toast.LENGTH_SHORT).show() }


        builder.setNeutralButton("Cancel") { _, _ ->

        }

    builder.setNegativeButton("Add new dealer ?") { _, _ ->

        NavHostFragment.findNavController(this)
            .navigate(R.id. action_enterCattleFragment_to_enterDealerFragment)

       }
    builder.show()
}


}






