package com.android.cattle360.ui.executive.exeHome.availableCattle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.R
import com.android.cattle360.data.apiResponse.DataLiveStockDetails
import com.android.cattle360.data.demmyModels.AvailableCattleModel
import com.android.cattle360.databinding.AvailableCattleItemBinding
import com.bumptech.glide.Glide


class CattleAdaptor :
    RecyclerView.Adapter<CattleAdaptor.CategoryViewHolder>() {

    var list: List<DataLiveStockDetails> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryViewHolder(var binding: AvailableCattleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setModel(cattleModel: DataLiveStockDetails) {

            binding.cattleModel = cattleModel
            val path:String= cattleModel.c_image as String

          if (path=="")
          {
              Glide.with(itemView)
                  .load(R.drawable.cw1)
                  .into(binding.cattleImage)
          }else {
              Glide.with(itemView)
                  .load(path)
                  .into(binding.cattleImage)
          }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            AvailableCattleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
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