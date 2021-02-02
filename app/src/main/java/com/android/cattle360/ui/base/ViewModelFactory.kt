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
import com.android.cattle360.ui.appStart.splash.SplashRepository
import com.android.cattle360.ui.appStart.splash.SplashViewModel

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
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}