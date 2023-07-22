package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {

    private val _currentConditionsData: MutableLiveData<CurrentConditions> = MutableLiveData()
    val currentConditionsData: LiveData<CurrentConditions>
        get() = _currentConditionsData


    fun viewAppeared() = viewModelScope.launch {
        _currentConditionsData.value = apiService.getCurrentData()
    }
}