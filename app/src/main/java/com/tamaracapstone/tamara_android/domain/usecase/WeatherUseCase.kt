package com.tamaracapstone.tamara_android.domain.usecase

import com.tamaracapstone.tamara_android.domain.contract.WeatherUseCaseContract
import com.tamaracapstone.tamara_android.domain.entity.WeatherEntity
import com.tamaracapstone.tamara_android.domain.interfaces.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WeatherUseCase(private val weatherRepository: WeatherRepository) : WeatherUseCaseContract {
    override fun invoke(lat: Float, lon: Float): Flow<WeatherEntity> {
        return weatherRepository.getWeatherData(lat, lon).map { weatherData ->
            WeatherEntity(
                jamCuaca = weatherData.jamCuaca,
                cuaca = weatherData.cuaca,
                tempC = weatherData.tempC,
                kecamatan = weatherData.kecamatan,
                recommendation = weatherData.recommendation,
                lon = weatherData.lon,
                lat = weatherData.lat
            )
        }
    }
}