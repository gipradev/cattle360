package com.android.cattle360.ui.user.home.Cattle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.R
import com.android.cattle360.data.apiResponse.DataLivestockImageList
import com.android.cattle360.data.demmyModels.CattleImageModel
import com.android.cattle360.databinding.CattleImageSliderItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class CattleImageAdaptor : RecyclerView.Adapter<CattleImageAdaptor.BannerViewHolder>() {

    var list: List<DataLivestockImageList> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class BannerViewHolder(var binding: CattleImageSliderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setModel(cattleImageModel: DataLivestockImageList) {
            binding.cattleImageModel = cattleImageModel
            val path:String=cattleImageModel.image_path

            if (path=="")
            {
                Glide.with(itemView)
                    .load(R.drawable.ic_no_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.bannerImage)
            }else {
                Glide.with(itemView)
                    .load(path)
                    .apply( RequestOptions().override(1000, 500))
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.bannerImage)
            }
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