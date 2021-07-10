package com.android.cattle360.ui.user.home.Cattle.BiddingSheet

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cattle360.data.apiResponse.GetInsertBidResponse
import com.android.cattle360.data.apiResponse.GetLiveStockDetailedViewResponse
import com.android.cattle360.data.demmyModels. BiddingModelViewModel
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.ui.base.BaseViewModel
import com.android.cattle360.ui.user.home.Cattle.CattleRepository
import kotlinx.coroutines.launch


class BiddingSheetViewModel(private val  repository: CattleRepository) : BaseViewModel() {

    var showSeekBar: ObservableField<Boolean> = ObservableField()
    var seekValue: ObservableField<Boolean> = ObservableField()
    var enterTextField: ObservableField<Boolean> = ObservableField()
    var seekSubmitButton: ObservableField<Boolean> = ObservableField()
    var enterSubmitButton: ObservableField<Boolean> = ObservableField()
    var biddingViewModel: ObservableField<BiddingModelViewModel> = ObservableField()

    val getbidamountResponse: LiveData<Resource<GetInsertBidResponse?>> = MutableLiveData()

    fun getBid(bid_amount: String,
               user_id: String,
               livestock_id: String) = viewModelScope.launch {
        getbidamountResponse as MutableLiveData
        getbidamountResponse.value = Resource.Loading
        //cattleResponse.value = cattleModelSupplier.cattle
        getbidamountResponse.value = repository.InsertBid(bid_amount,user_id,livestock_id)
        println(getbidamountResponse.value)
    }

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