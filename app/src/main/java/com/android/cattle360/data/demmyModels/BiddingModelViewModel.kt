package com.android.cattle360.data.demmyModels

import androidx.databinding.ObservableField


/**
 * DataLiveStockDetails class for bidding sheet view
 */
data class BiddingModelViewModel(

    var seekBar: ObservableField<Boolean> = ObservableField(true),
    var seekValue: ObservableField<Boolean> = ObservableField(true),
    var enterTextField: ObservableField<Boolean> = ObservableField(true),
    var seekSubmitButton: ObservableField<Boolean> = ObservableField(false),
    var enterSubmitButton: ObservableField<Boolean> = ObservableField(false),

    )