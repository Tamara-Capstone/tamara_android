package com.tamaracapstone.tamara_android.domain.contract

import com.tamaracapstone.tamara_android.domain.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

interface WeatherUseCaseContract {
    operator fun invoke(lat: Float, lon: Float): Flow<WeatherEntity>
}