package com.android.cattle360.data.demmyModels


data class CattleModel(
    val `data`: CattleDataModel,
    val product_image: List<CattleImageModel>,
    val status: String
)

object cattleModelSupplier {
    val cattle = CattleModel(
        data = cattleSupplier.cattleIt,
        product_image = cattleImageSuppiler.cattleImageItem,
        status = "Success")

}