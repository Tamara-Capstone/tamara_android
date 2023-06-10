package com.tamaracapstone.tamara_android.domain.interfaces

import com.tamaracapstone.tamara_android.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {
    val userData: Flow<UserEntity>
    suspend fun saveUser(userEntity: UserEntity)
    suspend fun clearUser()
}