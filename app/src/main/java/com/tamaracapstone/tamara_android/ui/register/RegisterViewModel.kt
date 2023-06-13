package com.tamaracapstone.tamara_android.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tamaracapstone.tamara_android.domain.contract.RegisterUseCaseContract
import com.tamaracapstone.tamara_android.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class RegisterViewModel(
    private val registerUseCase: RegisterUseCaseContract
) : ViewModel() {
    private val _registerViewState = MutableStateFlow(RegisterViewState())
    val registerViewState = _registerViewState.asStateFlow()

    fun registerUser(name: String, email: String, password: String) {
        registerUseCase(name, email, password).onEach { result ->
            _registerViewState.update {
                it.copy(resultRegisterUser = result)
            }
        }.launchIn(viewModelScope)
    }

    class Factory(
        private val registerUseCase: RegisterUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
                return RegisterViewModel(registerUseCase) as T
            }
            error("Unknown ViewModel class: $modelClass")
        }
    }
}