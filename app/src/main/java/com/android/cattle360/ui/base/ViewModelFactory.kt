package com.android.cattle360.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.cattle360.ui.appStart.login.LoginRepository
import com.android.cattle360.ui.appStart.login.LoginViewModel
import com.android.cattle360.ui.appStart.otp.OtpRepository
import com.android.cattle360.ui.appStart.otp.OtpViewModel
import com.android.cattle360.ui.appStart.password.PasswordRepository
import com.android.cattle360.ui.appStart.password.PasswordViewModel
import com.android.cattle360.ui.appStart.registration.RegistrationRepository
import com.android.cattle360.ui.appStart.registration.RegistrationViewModel
import com.android.cattle360.ui.appStart.searchLocation.SearchLocationRepository
import com.android.cattle360.ui.appStart.searchLocation.SearchLocationViewModel
import com.android.cattle360.ui.appStart.splash.SplashRepository
import com.android.cattle360.ui.appStart.splash.SplashViewModel
import com.android.cattle360.ui.executive.ExecutiveActivityViewModel
import com.android.cattle360.ui.executive.ExecutiveRepository
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import com.android.cattle360.ui.executive.addCattle.AddCattleViewModel
import com.android.cattle360.ui.executive.exeHome.ExecutiveHomeRepository
import com.android.cattle360.ui.executive.exeHome.ExecutiveHomeViewModel
import com.android.cattle360.ui.user.HomeActivityViewModel
import com.android.cattle360.ui.user.bidding.BiddingRepository
import com.android.cattle360.ui.user.bidding.BiddingViewModel
import com.android.cattle360.ui.user.bidding.live.LiveBiddingViewModel
import com.android.cattle360.ui.user.history.BiddingHistoryViewModel
import com.android.cattle360.ui.user.history.totalFragment.TotalAmountViewModel
import com.android.cattle360.ui.user.home.HomeRepository
import com.android.cattle360.ui.user.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                repository as LoginRepository
            ) as T
            modelClass.isAssignableFrom(OtpViewModel::class.java) -> OtpViewModel(
                repository as OtpRepository
            ) as T
            modelClass.isAssignableFrom(RegistrationViewModel::class.java) -> RegistrationViewModel(
                repository as RegistrationRepository
            ) as T
            modelClass.isAssignableFrom(PasswordViewModel::class.java) -> PasswordViewModel(
                repository as PasswordRepository
            ) as T
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(
                repository as SplashRepository
            ) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                repository as HomeRepository
            ) as T
            modelClass.isAssignableFrom(BiddingViewModel::class.java) -> BiddingViewModel(
                repository as BiddingRepository
            ) as T
            modelClass.isAssignableFrom(LiveBiddingViewModel::class.java) -> LiveBiddingViewModel(
                repository as BiddingRepository
            ) as T
            modelClass.isAssignableFrom(BiddingHistoryViewModel::class.java) -> BiddingHistoryViewModel(
                repository as BiddingRepository
            ) as T
            modelClass.isAssignableFrom(TotalAmountViewModel::class.java) -> TotalAmountViewModel(
                repository as BiddingRepository
            ) as T
            modelClass.isAssignableFrom(SearchLocationViewModel::class.java) -> SearchLocationViewModel(
                repository as SearchLocationRepository
            ) as T
            modelClass.isAssignableFrom(AddCattleViewModel::class.java) -> AddCattleViewModel(
                repository as AddCattleRepository
            ) as T

            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}