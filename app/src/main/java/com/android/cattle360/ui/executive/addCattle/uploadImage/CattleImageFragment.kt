package com.android.cattle360.ui.executive.addCattle.uploadImage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.CattleImageFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import com.android.cattle360.ui.executive.addCattle.uploadImage.uploadFragment.UploadFragment
import com.google.android.material.snackbar.Snackbar

class CattleImageFragment :
    BaseFragment<CattleImageViewModel, CattleImageFragmentBinding, AddCattleRepository>() {

    companion object {
        fun newInstance() = CattleImageFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CattleImageFragmentBinding {
        return CattleImageFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<CattleImageViewModel> {
        return CattleImageViewModel::class.java
    }

    override fun getFragmentRepository(): AddCattleRepository {
        return AddCattleRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        launchUploadFragment()



        binding.imagePreviousButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.imageNextButton.setOnClickListener {
          insert()
        }
    }

    private fun insert() {


    }

    private fun launchUploadFragment() {
        activity?.supportFragmentManager!!.beginTransaction().replace(
            R.id.uploadFragment,
            UploadFragment.newInstance(), UploadFragment::class.java.toString()
        ).commit()

    }

}