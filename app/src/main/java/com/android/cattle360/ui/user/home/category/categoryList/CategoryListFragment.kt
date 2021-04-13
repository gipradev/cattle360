package com.android.cattle360.ui.user.home.category.categoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.CategoryListFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.user.home.category.CategoryRepository

class CategoryListFragment :
    BaseFragment<CategoryListViewModel, CategoryListFragmentBinding, CategoryRepository>(),
    CategoryListAdaptor.OnLiveStockClickEvent {

    private val liveStockAdaptor: CategoryListAdaptor by lazy { CategoryListAdaptor(this) }

    companion object {
        fun newInstance() = CategoryListFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CategoryListFragmentBinding {
        return CategoryListFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<CategoryListViewModel> {
        return CategoryListViewModel::class.java
    }

    override fun getFragmentRepository(): CategoryRepository {
        return CategoryRepository(remoteDataSource.buildApi(ApiService::class.java))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val category_id =arguments?.getString("category_id")
println("passed value cat id.................."+category_id)
        binding.categoryRecyclerview.adapter = liveStockAdaptor
        viewModel.getCategoryList(category_id.toString())

        viewModel.categoryResponse.observe(viewLifecycleOwner, Observer {


//            liveStockAdaptor.list = it.categoryItem
            when (it) {
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    binding.categoryModel = it.value
                    println(".............."+{it.value?.data!! })
                    if (it.value?.status.equals("1")) {

                        liveStockAdaptor.list = it.value?.data!!
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


        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    override fun onItemClick() {

    }

    override fun onBidClick() {

    }

}