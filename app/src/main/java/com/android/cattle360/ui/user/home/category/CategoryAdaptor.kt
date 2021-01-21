package com.android.cattle360.ui.user.home.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.data.demmyModels.CategoryModel
import com.android.cattle360.databinding.CategoryItemBinding

class CategoryAdaptor(val clickListener: CategoryOnClick) : RecyclerView.Adapter<CategoryAdaptor.CategoryViewHolder>() {

    var list: List<CategoryModel> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryViewHolder(var binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setModel(categoryModel: CategoryModel) {
            binding.categoryModel = categoryModel

            binding.categoryImage.setOnClickListener {
                clickListener.onClick()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.setModel(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface CategoryOnClick{
        fun onClick()
    }
}