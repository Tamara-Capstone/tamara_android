package com.tamaracapstone.tamara_android.domain.usecase

import com.tamaracapstone.tamara_android.domain.contract.LogoutUseCaseContract
import com.tamaracapstone.tamara_android.domain.interfaces.UserPreferenceRepository

class LogoutUseCase(private val userPreferenceRepository: UserPreferenceRepository) :
    LogoutUseCaseContract {
    override suspend fun invoke() = userPreferenceRepository.clearUser()
}