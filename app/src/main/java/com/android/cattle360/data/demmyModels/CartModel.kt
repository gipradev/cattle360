package com.android.cattle360.data.demmyModels


data class CartModel(
    val `data`: CartDataModel,
    val cartList: List<CartListModel>,
    val status: String
)

object cartModelSupplier {
    val cart = CartModel(
        data = cartDataSupplier.cartData,
        cartList = cartListSuppiler.cartItem,
        status = "Success"
    )

}