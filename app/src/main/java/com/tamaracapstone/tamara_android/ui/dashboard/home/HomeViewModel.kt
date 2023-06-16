package com.tamaracapstone.tamara_android.ui.dashboard.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tamaracapstone.tamara_android.domain.usecase.WeatherUseCase
import com.tamaracapstone.tamara_android.utils.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class HomeViewModel(private val weatherUseCase: WeatherUseCase) : ViewModel() {
    private val _weatherDashboardViewState = MutableStateFlow(HomeViewState())
    val weatherDashboardViewState
    get() = _weatherDashboardViewState.asStateFlow()

    fun getWeatherDashboard(lat: Float, lon: Float) {
        weatherUseCase(lat, lon).onEach { result ->
            _weatherDashboardViewState.update {
                it.copy(resultWeather = ResultState.Success(result))
            }
        }.launchIn(viewModelScope)
    }

    class Factory(
        private val weatherUseCase: WeatherUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(weatherUseCase) as T
            }
            error("Unknown ViewModel class: $modelClass")
        }
    }
}