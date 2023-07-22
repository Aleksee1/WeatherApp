package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {

    private val _forecastConditionsData: MutableLiveData<ForecastConditions> = MutableLiveData()
    val forecastConditionsData: LiveData<ForecastConditions>
        get() = _forecastConditionsData


    fun viewAppeared() = viewModelScope.launch {
        _forecastConditionsData.value = apiService.getForecastData()
    }
}