package com.tamaracapstone.tamara_android.ui.dashboard.home

import com.tamaracapstone.tamara_android.domain.entity.WeatherEntity
import com.tamaracapstone.tamara_android.utils.ResultState

data class HomeViewState (
    val resultWeather: ResultState<WeatherEntity> = ResultState.Idle()
)