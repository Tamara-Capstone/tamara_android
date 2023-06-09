package com.tamaracapstone.tamara_android.domain.usecase

import com.tamaracapstone.tamara_android.domain.contract.GetUserUseCaseContract
import com.tamaracapstone.tamara_android.domain.entity.UserEntity
import com.tamaracapstone.tamara_android.domain.interfaces.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val userPreferenceRepository: UserPreferenceRepository) :
    GetUserUseCaseContract {
    override fun invoke(): Flow<UserEntity> = userPreferenceRepository.userData
}