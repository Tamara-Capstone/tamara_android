package com.tamaracapstone.tamara_android.data.response

data class WeatherResponse(
    val message: String,
    val data: WeatherData
)

data class WeatherData(
    val jamCuaca: String,
    val kodeCuaca: String,
    val cuaca: String,
    val humidity: String,
    val tempC: String,
    val tempF: String,
    val recommendation: String,
    val id: String,
    val propinsi: String,
    val kota: String,
    val kecamatan: String,
    val lat: Float,
    val lon: Float
)