package com.android.cattle360.ui.user.home.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.R
import com.android.cattle360.data.apiResponse.DataAllCategory
import com.android.cattle360.data.demmyModels.CategoryModel
import com.android.cattle360.databinding.CategoryItemBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlin.coroutines.coroutineContext

class CategoryAdaptor(val clickListener: CategoryOnClick) : RecyclerView.Adapter<CategoryAdaptor.CategoryViewHolder>() {
    lateinit var category_id:String
    var list: List<DataAllCategory> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryViewHolder(var binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setModel(categoryModel: DataAllCategory) {
            binding.categoryModel = categoryModel

             category_id=categoryModel.n_category_id

//            Glide.with(itemView)
//                .load(R.drawable.grass)
//                .into(binding.categoryImage)

            binding.categoryImage.setOnClickListener {
                clickListener.onClick(category_id)

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
        fun onClick(category_id:String)

    }
}