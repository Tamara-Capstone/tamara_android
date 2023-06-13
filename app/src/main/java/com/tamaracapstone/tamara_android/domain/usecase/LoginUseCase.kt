package com.tamaracapstone.tamara_android.domain.usecase

import com.tamaracapstone.tamara_android.domain.contract.LoginUseCaseContract
import com.tamaracapstone.tamara_android.domain.entity.UserEntity
import com.tamaracapstone.tamara_android.domain.interfaces.AuthRepository
import com.tamaracapstone.tamara_android.domain.interfaces.UserPreferenceRepository
import com.tamaracapstone.tamara_android.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoginUseCase(
    private val userPreferenceRepository: UserPreferenceRepository,
    private val authRepository: AuthRepository,
) : LoginUseCaseContract {
    override operator fun invoke(email: String, password: String): Flow<ResultState<String>> =
        flow {
            emit(ResultState.Loading())
            authRepository.login(email, password).catch {
                emit(ResultState.Error(it.message.toString()))
            }.collect { result ->
                if (result.error) {
                    emit(ResultState.Error(result.message))
                } else {
                    result.loginResult.let {
                        userPreferenceRepository.saveUser(
                            UserEntity(
                                it.userId, it.name, it.token
                            )
                        )
                    }
                    emit(ResultState.Success(result.message))
                }
            }
        }
}