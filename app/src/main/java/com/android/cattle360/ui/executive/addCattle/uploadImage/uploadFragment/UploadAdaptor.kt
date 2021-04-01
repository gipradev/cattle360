package com.android.cattle360.ui.executive.addCattle.uploadImage.uploadFragment

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.data.apiResponse.DataImageDefaultView


import com.android.cattle360.databinding.ImageUploadItemBinding
import com.bumptech.glide.Glide


class UploadAdaptor(private val listener : OnUploadClickEvent) :
    RecyclerView.Adapter<UploadAdaptor.CategoryViewHolder>() {

    var list: List<DataImageDefaultView> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryViewHolder(var binding: ImageUploadItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setModel(uploadModel: DataImageDefaultView) {

            binding.uploadModel = uploadModel
          val path:String=uploadModel.path
          Glide.with(itemView)
           .load(path)
              .into(binding.modelImage)

            binding.cameraButton.setOnClickListener {
                listener.onCameraClick(binding.modelImage , adapterPosition)

            }

        }

        fun uploadImage(position: Int?, scaledBitmap: Bitmap) {


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ImageUploadItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.setModel(list[position])

    }

    override fun getItemCount(): Int = list.size



    interface OnUploadClickEvent {
        fun onCameraClick(modelImage: ImageView, adapterPosition: Int)

    }
}