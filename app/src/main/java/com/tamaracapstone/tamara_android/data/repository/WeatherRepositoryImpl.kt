package com.tamaracapstone.tamara_android.data.repository

import com.tamaracapstone.tamara_android.data.source.remote.ApiService
import com.tamaracapstone.tamara_android.domain.interfaces.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepositoryImpl(private val api: ApiService) : WeatherRepository {

    override fun getWeatherData(lat: Float, lon: Float): Flow<WeatherRepository.WeatherData> =
        flow {
            val response = api.getWeather(lat, lon)
            val weatherData = WeatherRepository.WeatherData(
                jamCuaca = response.data.jamCuaca,
                cuaca = response.data.cuaca,
                tempC = response.data.tempC,
                kecamatan = response.data.kecamatan,
                recommendation = response.data.recommendation,
                lon = response.data.lon,
                lat = response.data.lat
            )
            emit(weatherData)
        }.flowOn(Dispatchers.IO)
}