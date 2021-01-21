package com.android.cattle360.data.demmyModels


data class CartModel(
    val `data`: CartDataModel,
    val product_image: List<CartListModel>,
    val status: String
)

object cartModelSupplier {
    val cart = CartModel(
        data = cartDataSupplier.cartData,
        product_image = cartListSuppiler.cartItem,
        status = "Success"
    )

}