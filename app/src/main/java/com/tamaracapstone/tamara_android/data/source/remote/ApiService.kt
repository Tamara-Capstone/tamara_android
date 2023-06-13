package com.tamaracapstone.tamara_android.data.source.remote

import com.tamaracapstone.tamara_android.data.response.GeneralResponse
import com.tamaracapstone.tamara_android.data.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun register(
        @Body requestBody: HashMap<String, String>
    ): GeneralResponse

    @POST("login")
    suspend fun login(
        @Body requestBody: HashMap<String, String>
    ): LoginResponse


}