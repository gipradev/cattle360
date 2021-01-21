package com.android.cattle360.ui.user.home.category.categoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
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
        return CategoryRepository()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        binding.categoryRecyclerview.adapter = liveStockAdaptor
        viewModel.getCategoryList()

        viewModel.categoryResponse.observe(viewLifecycleOwner, Observer {

            binding.categoryModel = it
            liveStockAdaptor.list = it.categoryItem
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