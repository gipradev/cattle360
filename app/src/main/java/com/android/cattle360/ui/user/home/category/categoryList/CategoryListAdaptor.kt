package com.android.cattle360.ui.user.home.category.categoryList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.R
import com.android.cattle360.data.apiResponse.DataCategoryList
import com.android.cattle360.data.demmyModels.CategoryLiveStockModel
import com.android.cattle360.databinding.CategoryListItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class CategoryListAdaptor(private val clickListener: OnLiveStockClickEvent) :
    RecyclerView.Adapter<CategoryListAdaptor.CategoryViewHolder>() {

    var list: List<DataCategoryList> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryViewHolder(var binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setModel(categoryListModel: DataCategoryList) {

            binding.model = categoryListModel
            val path:String= categoryListModel.c_image

            if (path=="")
            {
                Glide.with(itemView)
                    .load(R.drawable.cow_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imageView)
            }else {
                Glide.with(itemView)
                    .load(path)
                    .apply( RequestOptions().override(400, 200))
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imageView)
            }
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