package com.android.cattle360.ui.user.home.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.data.demmyModels.BannerModel
import com.android.cattle360.databinding.BannerItemBinding

class BannerAdaptor : RecyclerView.Adapter<BannerAdaptor.BannerViewHolder>() {

    var list: List<BannerModel> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class BannerViewHolder(var binding: BannerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setModel(bannerModel: BannerModel) {

            binding.bannerModel = bannerModel
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            BannerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.setModel(list[position])
    }

    override fun getItemCount(): Int = list.size
}