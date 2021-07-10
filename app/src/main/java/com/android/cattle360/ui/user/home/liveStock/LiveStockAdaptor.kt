package com.android.cattle360.ui.user.home.liveStock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.R
import com.android.cattle360.data.apiResponse.DataAllLivestock
import com.android.cattle360.data.apiResponse.DataLiveStockDetails
import com.android.cattle360.data.demmyModels.LiveStockModel
import com.android.cattle360.databinding.LiveStockItemBinding
import com.bumptech.glide.Glide


class LiveStockAdaptor(private val clickListener: OnLiveStockClickEvent) :
    RecyclerView.Adapter<LiveStockAdaptor.CategoryViewHolder>() {
lateinit var livestock_id:String
    var list: List<DataAllLivestock> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryViewHolder(var binding: LiveStockItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setModel(liveStockModel: DataAllLivestock) {

            binding.liveStockModel = liveStockModel
            val path:String= liveStockModel.c_image as String
            livestock_id=liveStockModel.n_livestock_id
            if (path=="")
            {
                Glide.with(itemView)
                    .load(R.drawable.cw1)
                    .into(binding.livestockImage)
            }else {
                Glide.with(itemView)
                    .load(path)
                    .into(binding.livestockImage)
            }

            binding.cattleItem.setOnClickListener {
                clickListener.onItemClick(livestock_id)
            }

            binding.bidButton.setOnClickListener {
                clickListener.onBidClick(livestock_id)
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
        fun onItemClick(livestock_id:String)
        fun onBidClick(livestock_id:String)
    }
}