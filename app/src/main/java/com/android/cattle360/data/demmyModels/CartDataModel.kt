package com.android.cattle360.data.demmyModels

data class CartDataModel(
    val totalAmount: String,
    val deliveryCharge: String,
    val taxAmount: String,
    val itemTotal: String
)

object cartDataSupplier {
    val cartData = CartDataModel(
        totalAmount = "Cattle One",
        deliveryCharge = "Cows",
        taxAmount = "152.00",
        itemTotal = "20000.00",
    )
}