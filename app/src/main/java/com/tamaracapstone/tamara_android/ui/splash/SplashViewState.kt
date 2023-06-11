package com.tamaracapstone.tamara_android.ui.splash

import com.tamaracapstone.tamara_android.utils.ResultState

data class SplashViewState (
    val resultIsLoggedIn: ResultState<Boolean> = ResultState.Idle()
)