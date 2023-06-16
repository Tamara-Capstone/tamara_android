package com.tamaracapstone.tamara_android.data.response

data class GetWeatherDashboardResponse(
	val tempF: String,
	val kota: String,
	val kodeCuaca: String,
	val cuaca: String,
	val recommendation: String,
	val lon: String,
	val propinsi: String,
	val jamCuaca: String,
	val kecamatan: String,
	val humidity: String,
	val id: String,
	val tempC: String,
	val lat: String
)

