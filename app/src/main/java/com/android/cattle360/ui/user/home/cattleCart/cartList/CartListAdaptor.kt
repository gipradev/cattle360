package com.android.cattle360.ui.user.home.cattleCart.cartList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.data.demmyModels.CartListModel
import com.android.cattle360.databinding.CartListItemBinding


class CartListAdaptor(private val clickListener: OnCartListClickEvent) :
    RecyclerView.Adapter<CartListAdaptor.CategoryViewHolder>() {

    var list: List<CartListModel> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CategoryViewHolder(var binding: CartListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setModel(cartListModel: CartListModel) {

            binding.model = cartListModel


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CartListItemBinding.inflate(
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

    interface OnCartListClickEvent {
        fun onDeleteClick()
    }
}