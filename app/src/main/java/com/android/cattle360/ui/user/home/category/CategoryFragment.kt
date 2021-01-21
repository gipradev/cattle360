package com.android.cattle360.ui.user.home.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
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
        return CategoryRepository()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.categoryRecycler.adapter = categoryAdapter

        viewModel.getCategoryList()

        viewModel.categoryResponse.observe(viewLifecycleOwner, Observer {
          categoryAdapter.list = it
        })

    }

    override fun onClick() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_homeFragment_to_categoryListFragment)
    }

}