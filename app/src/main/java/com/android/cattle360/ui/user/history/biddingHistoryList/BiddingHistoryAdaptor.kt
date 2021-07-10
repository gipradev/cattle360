package com.android.cattle360.ui.user.history.biddingHistoryList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.data.apiResponse.DataBiddingHistory
import com.android.cattle360.data.demmyModels.LiveBidModel
import com.android.cattle360.databinding.HistoryBiddedItemBinding


class BiddingHistoryAdaptor :
    RecyclerView.Adapter<BiddingHistoryAdaptor.CategoryViewHolder>() {

    var list: List<DataBiddingHistory> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryViewHolder(var binding: HistoryBiddedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setModel(liveBidModel: DataBiddingHistory) {
            binding.liveHistoryBidModel = liveBidModel

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            HistoryBiddedItemBinding.inflate(
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