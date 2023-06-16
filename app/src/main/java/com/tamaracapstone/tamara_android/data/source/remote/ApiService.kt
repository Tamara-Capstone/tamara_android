package com.tamaracapstone.tamara_android.data.source.remote

import com.tamaracapstone.tamara_android.data.response.GeneralResponse
import com.tamaracapstone.tamara_android.data.response.GetUserResponse
import com.tamaracapstone.tamara_android.data.response.LoginResponse
import com.tamaracapstone.tamara_android.data.response.WeatherResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("register")
    suspend fun register(
        @Body requestBody: HashMap<String, String>
    ): GeneralResponse

    @POST("login")
    suspend fun login(
        @Body requestBody: HashMap<String, String>
    ): LoginResponse

    @GET("user")
    suspend fun userDataLogin(
        @Body requestBody: HashMap<String, String>
    ): GetUserResponse

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float
    ): WeatherResponse
}