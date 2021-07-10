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
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository
import com.android.cattle360.ui.executive.addCattle.enterCattle.EnterCattleViewModel
import com.android.cattle360.ui.executive.addCattle.enterDealerDetails.EnterDealerViewModel
import com.android.cattle360.ui.executive.addCattle.enterLocation.EnterLocationViewModel
import com.android.cattle360.ui.executive.addCattle.uploadImage.CattleImageViewModel
import com.android.cattle360.ui.executive.addCattle.uploadImage.uploadFragment.UploadViewModel
import com.android.cattle360.ui.executive.exeHome.ExecutiveHomeRepository
import com.android.cattle360.ui.executive.exeHome.ExecutiveHomeViewModel
import com.android.cattle360.ui.executive.exeHome.availableCattle.AvailableCattleViewModel
import com.android.cattle360.ui.executive.exeProfile.ExecutiveProfileRepository
import com.android.cattle360.ui.executive.exeProfile.ExecutiveProfileViewModel
import com.android.cattle360.ui.user.bidding.BiddingRepository
import com.android.cattle360.ui.user.bidding.BiddingViewModel
import com.android.cattle360.ui.user.bidding.live.LiveBiddingViewModel
import com.android.cattle360.ui.user.history.BiddingHistoryViewModel
import com.android.cattle360.ui.user.history.biddingHistoryList.BiddingHistoryListViewModel
import com.android.cattle360.ui.user.history.totalFragment.TotalAmountViewModel
import com.android.cattle360.ui.user.home.Cattle.BiddingSheet.BiddingSheetViewModel
import com.android.cattle360.ui.user.home.Cattle.CattleRepository
import com.android.cattle360.ui.user.home.Cattle.CattleViewModel
import com.android.cattle360.ui.user.home.Cattle.cattleImageSlider.ImageSliderViewModel
import com.android.cattle360.ui.user.home.HomeRepository
import com.android.cattle360.ui.user.home.HomeViewModel
import com.android.cattle360.ui.user.home.banner.BannerRepository
import com.android.cattle360.ui.user.home.banner.BannerViewModel
import com.android.cattle360.ui.user.home.category.CategoryRepository
import com.android.cattle360.ui.user.home.category.CategoryViewModel
import com.android.cattle360.ui.user.home.category.categoryList.CategoryListViewModel
import com.android.cattle360.ui.user.home.cattleCart.CattleCartRepository
import com.android.cattle360.ui.user.home.cattleCart.CattleCartViewModel
import com.android.cattle360.ui.user.home.liveStock.LiveStockRepository
import com.android.cattle360.ui.user.home.liveStock.LiveStockViewModel
import com.android.cattle360.ui.user.home.location.LocationRepository
import com.android.cattle360.ui.user.home.location.LocationViewModel
import com.android.cattle360.ui.user.profile.ProfileRepository
import com.android.cattle360.ui.user.profile.ProfileViewModel
import com.android.cattle360.ui.user.profile.wallet.WalletRepository
import com.android.cattle360.ui.user.profile.wallet.WalletViewModel

@Suppress("UNCHECKED_CAST")
class     ViewModelFactory(
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
            modelClass.isAssignableFrom(BannerViewModel::class.java) -> BannerViewModel(
                repository as BannerRepository
            ) as T
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> CategoryViewModel(
                repository as CategoryRepository
            ) as T
            modelClass.isAssignableFrom(CattleViewModel::class.java) -> CattleViewModel(
                repository as CattleRepository
            ) as T
            modelClass.isAssignableFrom(CattleCartViewModel::class.java) -> CattleCartViewModel(
                repository as CattleCartRepository
            ) as T
            modelClass.isAssignableFrom(LiveStockViewModel::class.java) -> LiveStockViewModel(
                repository as LiveStockRepository
            ) as T
            modelClass.isAssignableFrom(LocationViewModel::class.java) -> LocationViewModel(
                repository as LocationRepository
            ) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(
                repository as ProfileRepository
            ) as T
            modelClass.isAssignableFrom(WalletViewModel::class.java) -> WalletViewModel(
                repository as WalletRepository
            ) as T
            modelClass.isAssignableFrom(BiddingViewModel::class.java) -> BiddingViewModel(
                repository as BiddingRepository
            ) as T
            modelClass.isAssignableFrom(BiddingHistoryViewModel::class.java) -> BiddingHistoryViewModel(
                repository as BiddingRepository
            ) as T
            modelClass.isAssignableFrom(SearchLocationViewModel::class.java) -> SearchLocationViewModel(
                repository as SearchLocationRepository
            ) as T
            modelClass.isAssignableFrom(ExecutiveHomeViewModel::class.java) -> ExecutiveHomeViewModel(
                repository as ExecutiveHomeRepository
            ) as T
            modelClass.isAssignableFrom(ExecutiveProfileViewModel::class.java) -> ExecutiveProfileViewModel(
                repository as ExecutiveProfileRepository
            ) as T
            modelClass.isAssignableFrom(AvailableCattleViewModel::class.java) -> AvailableCattleViewModel(
                repository as ExecutiveHomeRepository
            ) as T
            modelClass.isAssignableFrom(CattleImageViewModel::class.java) -> CattleImageViewModel(
                repository as AddCattleRepository
            ) as T
            modelClass.isAssignableFrom(EnterCattleViewModel::class.java) -> EnterCattleViewModel(
                repository as AddCattleRepository
            ) as T
            modelClass.isAssignableFrom(EnterLocationViewModel::class.java) -> EnterLocationViewModel(
                repository as AddCattleRepository
            ) as T
            modelClass.isAssignableFrom(UploadViewModel::class.java) -> UploadViewModel(
                repository as AddCattleRepository
            ) as T
            modelClass.isAssignableFrom(CattleImageViewModel::class.java) -> CattleImageViewModel(
                repository as AddCattleRepository
            ) as T
            modelClass.isAssignableFrom(EnterDealerViewModel::class.java) -> EnterDealerViewModel(
                repository as AddCattleRepository
            ) as T
            modelClass.isAssignableFrom(CategoryListViewModel::class.java) -> CategoryListViewModel(
                repository as CategoryRepository
            ) as T
            modelClass.isAssignableFrom(ImageSliderViewModel::class.java) -> ImageSliderViewModel(
                repository as CattleRepository
            ) as T
            modelClass.isAssignableFrom(LiveBiddingViewModel::class.java) -> LiveBiddingViewModel(
                repository as BiddingRepository
            ) as T
            modelClass.isAssignableFrom( BiddingHistoryListViewModel::class.java) ->  BiddingHistoryListViewModel(
                repository as BiddingRepository
            ) as T
            modelClass.isAssignableFrom( BiddingSheetViewModel::class.java) ->  BiddingSheetViewModel(
                repository as CattleRepository
            ) as T
            modelClass.isAssignableFrom( TotalAmountViewModel::class.java) ->  TotalAmountViewModel(
                repository as BiddingRepository
            ) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}