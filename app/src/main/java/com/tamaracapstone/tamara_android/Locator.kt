package com.tamaracapstone.tamara_android

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.tamaracapstone.tamara_android.data.repository.AuthRepositoryImpl
import com.tamaracapstone.tamara_android.data.source.local.UserPreferenceImpl
import com.tamaracapstone.tamara_android.data.source.remote.ApiConfig
import com.tamaracapstone.tamara_android.domain.usecase.GetUserUseCase
import com.tamaracapstone.tamara_android.domain.usecase.LoginUseCase
import com.tamaracapstone.tamara_android.domain.usecase.RegisterUseCase
import com.tamaracapstone.tamara_android.ui.login.LoginViewModel
import com.tamaracapstone.tamara_android.ui.register.RegisterViewModel
import com.tamaracapstone.tamara_android.ui.splash.SplashViewModel

object Locator {
    private var application: Application? = null

    private inline val requireApplication
        get() = application ?: error("Missing call: initWith(application)")

    fun initWith(application: Application) {
        this.application = application
    }

    // Data Store
    private val Context.dataStore by preferencesDataStore(name = "user_preferences")

    // ViewModel Factory
    val loginViewModelFactory
        get() = LoginViewModel.Factory(
            loginUseCase = loginUseCase
        )
    val registerViewModelFactory
        get() = RegisterViewModel.Factory(
            registerUseCase = registerUseCase
        )
    val splashViewModelFactory
        get() = SplashViewModel.Factory(
            getUserUseCase = getUserUseCase
        )

    //usecase injection
    private val loginUseCase get() = LoginUseCase(userPreferencesRepository, authRepository)
    private val registerUseCase get() = RegisterUseCase(authRepository)
    private val getUserUseCase get() = GetUserUseCase(userPreferencesRepository)

    //repository injection
    private val userPreferencesRepository by lazy {
        UserPreferenceImpl(requireApplication.dataStore)
    }
    private val authRepository by lazy {
        AuthRepositoryImpl(ApiConfig(requireApplication.dataStore).apiService)
    }
}