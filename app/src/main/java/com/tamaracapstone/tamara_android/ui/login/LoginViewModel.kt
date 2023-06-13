package com.tamaracapstone.tamara_android.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tamaracapstone.tamara_android.domain.contract.LoginUseCaseContract
import com.tamaracapstone.tamara_android.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class LoginViewModel(private val loginUseCase: LoginUseCaseContract) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginViewState())
    val loginState = _loginState.asStateFlow()


    fun doLogin(email: String, password: String) {
        loginUseCase(email, password)
            .onEach { result ->
                _loginState.update {
                    it.copy(resultVerifyUser = result)
                }
            }.launchIn(viewModelScope)
    }

    class Factory(
        private val loginUseCase: LoginUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(loginUseCase) as T
            }
            error("Unknown ViewModel class: $modelClass")
        }
    }
}