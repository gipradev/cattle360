package com.android.cattle360.ui.user.bidding.live

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.data.demmyModels.LiveBidModel
import com.android.cattle360.databinding.LiveBiddedItemBinding


class LiveBidAdaptor :
    RecyclerView.Adapter<LiveBidAdaptor.CategoryViewHolder>() {

    var list: List<LiveBidModel> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryViewHolder(var binding: LiveBiddedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setModel(liveBidModel: LiveBidModel) {

            binding.liveBidModel = liveBidModel

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LiveBiddedItemBinding.inflate(
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