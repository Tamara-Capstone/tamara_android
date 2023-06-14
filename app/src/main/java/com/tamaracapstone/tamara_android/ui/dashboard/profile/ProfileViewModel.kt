package com.tamaracapstone.tamara_android.ui.dashboard.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tamaracapstone.tamara_android.domain.contract.GetUserUseCaseContract
import com.tamaracapstone.tamara_android.domain.contract.LogoutUseCaseContract
import com.tamaracapstone.tamara_android.domain.usecase.GetUserUseCase
import com.tamaracapstone.tamara_android.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(

    private val getUserUseCase: GetUserUseCaseContract,
    private val logoutUseCase: LogoutUseCaseContract
) : ViewModel() {

    private val _profileState = MutableStateFlow(ProfileViewState())
    val profileState = _profileState.asStateFlow()

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }

    fun getUser() {
        viewModelScope.launch {
            getUserUseCase().collect { user ->
                _profileState.update {
                    it.copy(name = user.name)
                }
            }
        }
    }

    class Factory(
        private val getUserUseCase: GetUserUseCase,
        private val logoutUseCase: LogoutUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
                return ProfileViewModel(getUserUseCase, logoutUseCase) as T
            }
            error("Unknown ViewModel class: $modelClass")
        }
    }
}