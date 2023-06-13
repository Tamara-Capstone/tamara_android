package com.tamaracapstone.tamara_android.domain.contract

import com.tamaracapstone.tamara_android.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface RegisterUseCaseContract {
    operator fun invoke(name: String, email: String, password: String): Flow<ResultState<String>>
}