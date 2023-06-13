package com.tamaracapstone.tamara_android.domain.contract

import com.tamaracapstone.tamara_android.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface GetUserUseCaseContract {
    operator fun invoke(): Flow<UserEntity>
}