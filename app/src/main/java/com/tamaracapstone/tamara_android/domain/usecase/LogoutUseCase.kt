package com.tamaracapstone.tamara_android.domain.usecase

import com.tamaracapstone.tamara_android.domain.interfaces.UserPreferenceRepository

class LogoutUseCase(private val userPreferenceRepository: UserPreferenceRepository) {
    suspend operator fun invoke() {
        userPreferenceRepository.clearUser()
    }
}