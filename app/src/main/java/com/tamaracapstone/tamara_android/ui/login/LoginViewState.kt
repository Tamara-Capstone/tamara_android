package com.tamaracapstone.tamara_android.ui.login

import com.tamaracapstone.tamara_android.utils.ResultState

data class LoginViewState(
    val resultVerifyUser: ResultState<String> = ResultState.Idle()
)