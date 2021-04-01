package com.android.cattle360.ui.user.home.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.CategoryFragmentBinding
import com.android.cattle360.ui.base.BaseFragment

class CategoryFragment : BaseFragment<CategoryViewModel,CategoryFragmentBinding,CategoryRepository>() ,
CategoryAdaptor.CategoryOnClick{

    private val categoryAdapter: CategoryAdaptor by lazy { CategoryAdaptor(this) }


    companion object {
        fun newInstance() = CategoryFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CategoryFragmentBinding {
        return CategoryFragmentBinding.inflate(layoutInflater,container,false)
    }

    override fun getViewModel(): Class<CategoryViewModel> = CategoryViewModel::class.java

    override fun getFragmentRepository(): CategoryRepository {
        return CategoryRepository(remoteDataSource.buildApi(ApiService::class.java))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.categoryRecycler.adapter = categoryAdapter

        viewModel.getCategoryList()

        viewModel.categoryResponse.observe(viewLifecycleOwner, Observer {
           // categoryAdapter.list = it

            when (it) {
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    println(".............."+{it.value?.data!! })
                    if (it.value?.status.equals("1")) {

                        categoryAdapter.list = it.value?.data!!
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

    override fun onClick() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_homeFragment_to_categoryListFragment)
    }

}