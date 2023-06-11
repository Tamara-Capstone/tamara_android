package com.tamaracapstone.tamara_android.ui.register

import com.tamaracapstone.tamara_android.utils.ResultState

data class RegisterViewState (
    val resultRegisterUser: ResultState<String> = ResultState.Idle()
)