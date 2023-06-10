package com.tamaracapstone.tamara_android.domain.interfaces

import com.tamaracapstone.tamara_android.data.response.GeneralResponse
import com.tamaracapstone.tamara_android.data.response.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun register(email: String, password: String, name: String): Flow<GeneralResponse>
    fun login(email: String, password: String): Flow<LoginResponse>
}