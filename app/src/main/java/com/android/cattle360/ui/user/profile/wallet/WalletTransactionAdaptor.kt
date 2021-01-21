package com.android.cattle360.ui.user.profile.wallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cattle360.data.demmyModels.TransactionModel
import com.android.cattle360.databinding.WalletTransactionItemBinding


class WalletTransactionAdaptor :
    RecyclerView.Adapter<WalletTransactionAdaptor.MyViewHolder>() {

    var list: List<TransactionModel> = arrayListOf()


    inner class MyViewHolder(var binding: WalletTransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun setModel(transactionModel: TransactionModel) {

            binding.transactionModel = transactionModel

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            WalletTransactionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.setModel(list[position])

    }

    override fun getItemCount(): Int = list.size


    interface ItemClick {
        fun onItemSelected(id: String)
    }
}