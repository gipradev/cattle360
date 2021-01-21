package com.android.cattle360.ui.user.home.Cattle.BiddingSheet

import androidx.databinding.ObservableField
import com.android.cattle360.data.demmyModels.BiddingModelViewModel
import com.android.cattle360.ui.base.BaseViewModel

class BiddingSheetViewModel : BaseViewModel() {

    var showSeekBar: ObservableField<Boolean> = ObservableField()
    var seekValue: ObservableField<Boolean> = ObservableField()
    var enterTextField: ObservableField<Boolean> = ObservableField()
    var seekSubmitButton: ObservableField<Boolean> = ObservableField()
    var enterSubmitButton: ObservableField<Boolean> = ObservableField()
    var biddingViewModel: ObservableField<BiddingModelViewModel> = ObservableField()

    /**
     * Method to add header data
     */
    fun showSeekView(data: BiddingModelViewModel) {
        biddingViewModel.set(data)
        displaySeekBar(true)
    }

    /**
     * Method to add header data
     */
    fun showManualView(data: BiddingModelViewModel) {
        biddingViewModel.set(data)
        displayTextInput(true)
    }

    /**
     * Method to display seekBar
     */
    fun displaySeekBar(status: Boolean = true) {
        showSeekBar.set(status)
        seekValue.set(status)
        seekSubmitButton.set(status)
    }


    /**
     * Method to display TextField
     */
    fun displayTextInput(status: Boolean = true) {
        enterTextField.set(status)
        enterSubmitButton.set(status)
    }
}