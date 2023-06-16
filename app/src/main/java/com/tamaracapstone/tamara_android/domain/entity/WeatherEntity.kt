package com.tamaracapstone.tamara_android.domain.entity

data class WeatherEntity(
    val jamCuaca: String,
    val cuaca: String,
    val tempC: String,
    val kecamatan: String,
    val recommendation: String,
    val lon: Float,
    val lat: Float
)