package com.android.cattle360.ui.user.home.Cattle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.data.demmyModels.CattleImageModel
import com.android.cattle360.databinding.CattleImageSliderItemBinding

class CattleImageAdaptor : RecyclerView.Adapter<CattleImageAdaptor.BannerViewHolder>() {

    var list: List<CattleImageModel> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class BannerViewHolder(var binding: CattleImageSliderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setModel(cattleImageModel: CattleImageModel) {
            binding.cattleImageModel = cattleImageModel
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            CattleImageSliderItemBinding.inflate(
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