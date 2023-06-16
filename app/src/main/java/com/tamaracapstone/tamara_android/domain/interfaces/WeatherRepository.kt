package com.tamaracapstone.tamara_android.domain.interfaces

import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    data class WeatherData(
        val jamCuaca: String,
        val cuaca: String,
        val tempC: String,
        val kecamatan: String,
        val recommendation: String,
        val lon: Float,
        val lat: Float
    )

    fun getWeatherData(lat: Float, lon: Float): Flow<WeatherData>
}