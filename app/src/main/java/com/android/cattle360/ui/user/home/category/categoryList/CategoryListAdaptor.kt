package com.android.cattle360.ui.user.home.category.categoryList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.data.demmyModels.CategoryLiveStockModel
import com.android.cattle360.databinding.CategoryListItemBinding


class CategoryListAdaptor(private val clickListener: OnLiveStockClickEvent) :
    RecyclerView.Adapter<CategoryListAdaptor.CategoryViewHolder>() {

    var list: List<CategoryLiveStockModel> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryViewHolder(var binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setModel(categoryListModel: CategoryLiveStockModel) {

            binding.model = categoryListModel

            binding.cattleItem.setOnClickListener {
                clickListener.onItemClick()
            }

            binding.bidButton.setOnClickListener {
                clickListener.onBidClick()
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryListItemBinding.inflate(
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

    interface OnLiveStockClickEvent {
        fun onItemClick()
        fun onBidClick()
    }
}