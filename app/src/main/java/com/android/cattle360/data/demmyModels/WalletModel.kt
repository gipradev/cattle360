package com.android.cattle360.data.demmyModels


data class WalletModel(
    val totalAmount: String,
    val transaction: List<TransactionModel>,
    val status: String
)

object walletModelSupplier {
    val wallet = WalletModel(
        totalAmount = "1524.00",
        transaction = transcationSupplier.transactionItem,
        status = "Success"
    )

}