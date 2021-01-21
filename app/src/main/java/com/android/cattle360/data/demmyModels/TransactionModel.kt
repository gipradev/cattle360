package com.android.cattle360.data.demmyModels



data class TransactionModel(
    var id: Int? = null,
    var title: String? = null,
    var date: String? = null,
    var amount: String? = null,
    var status: Boolean? = null,
)

object transcationSupplier {
    val transactionItem = listOf(
        TransactionModel(id = 0, title = "One", date = "14/02/2020", amount = "100.00" ,status = true),
        TransactionModel(id = 1, title = "Two", date = "24/08/2020", amount = "130.00",status = false),
        TransactionModel(id = 2, title = "Three", date = "12/04/2020", amount = "54.00",status = true),
        TransactionModel(id = 3, title = "Three",date = "12/01/2020", amount =  "16.00",status = false),
        TransactionModel(id = 4, title = "Two", date = "13/02/2020", amount = "200.00",status = true),
        TransactionModel(id = 5, title = "Three", date = "02/02/2020", amount = "75.24",status = false),
        TransactionModel(id = 3, title = "Three",date = "12/01/2020", amount =  "16.00",status = false),
        TransactionModel(id = 0, title = "One", date = "14/02/2020", amount = "100.00" ,status = true),


        )
}


