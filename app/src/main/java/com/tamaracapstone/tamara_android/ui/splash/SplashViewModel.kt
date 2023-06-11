package com.tamaracapstone.tamara_android.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tamaracapstone.tamara_android.domain.usecase.GetUserUseCase
import com.tamaracapstone.tamara_android.utils.ResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(private val getUserUseCase: GetUserUseCase) : ViewModel() {
    private val _splashState = MutableStateFlow(SplashViewState())
    val splashState = _splashState.asStateFlow()

    init {
        getIsLoggedIn()
    }

    private fun getIsLoggedIn() {
        viewModelScope.launch {
            getUserUseCase().collect { user ->
                delay(3000)
                _splashState.update {
                    it.copy(resultIsLoggedIn = ResultState.Success(user.token.isNotEmpty()))
                }
            }
        }
    }

    class Factory(
        private val getUserUseCase: GetUserUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
                return SplashViewModel(getUserUseCase) as T
            }
            error("Unknown ViewModel class: $modelClass")
        }
    }
}