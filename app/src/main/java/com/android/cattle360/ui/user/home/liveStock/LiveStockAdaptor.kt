package com.android.cattle360.ui.user.home.liveStock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.data.demmyModels.LiveStockModel
import com.android.cattle360.databinding.LiveStockItemBinding


class LiveStockAdaptor(private val clickListener: OnLiveStockClickEvent) :
    RecyclerView.Adapter<LiveStockAdaptor.CategoryViewHolder>() {

    var list: List<LiveStockModel> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryViewHolder(var binding: LiveStockItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setModel(liveStockModel: LiveStockModel) {

            binding.liveStockModel = liveStockModel

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
            LiveStockItemBinding.inflate(
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