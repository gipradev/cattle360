package com.android.cattle360.ui.executive.exeHome.availableCattle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.AvailableCattleFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.exeHome.ExecutiveHomeRepository

class AvailableCattleFragment :
    BaseFragment<AvailableCattleViewModel, AvailableCattleFragmentBinding, ExecutiveHomeRepository>() {

    private val cattleAdaptor: CattleAdaptor by lazy { CattleAdaptor() }

    companion object {
        fun newInstance() = AvailableCattleFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): AvailableCattleFragmentBinding {
        return AvailableCattleFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<AvailableCattleViewModel> {
        return AvailableCattleViewModel::class.java
    }

    override fun getFragmentRepository(): ExecutiveHomeRepository {
        return ExecutiveHomeRepository(remoteDataSource.buildApi(ApiService::class.java))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val userid:String = "2"
        binding.cattleRecycler.adapter = cattleAdaptor
        viewModel.getCattleList(userid)

        viewModel.cattleResponse.observe(viewLifecycleOwner, Observer {
            //cattleAdaptor.list = it
            when (it) {
                is Resource.Loading -> {
                    println("Loading ")
                }
                is Resource.Success -> {

                    if (it.value?.status.equals("1")) {

                        cattleAdaptor.list = it.value?.data!!
                        println(".............................list"+ it.value.data)

                    }
                    else
                    {
                        println(".............................no data found or error")
                    }
                }

                is Resource.Failure -> {
                    println("Failure  : $it")
                }

            }



        })


    }

}