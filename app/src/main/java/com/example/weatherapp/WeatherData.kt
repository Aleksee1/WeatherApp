package com.example.weatherapp

import com.squareup.moshi.Json

data class WeatherData(
    @Json(name = "icon") val iconName: String,
    @Json(name = "description") val description: String,
)

data class CurrentConditions(
    @Json(name = "name") val cityName: String,
    @Json(name = "weather") val weatherData: List<WeatherData>,
    @Json(name = "main") val conditions: CurrentConditionsData,
)

data class CurrentConditionsData(
    @Json(name = "temp") val temp: Float,
    @Json(name = "feels_like") val feelsLike: Float,
    @Json(name = "temp_min") val tempMin: Float,
    @Json(name = "temp_max") val tempMax: Float,
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "pressure") val pressure: Int,
)

data class ForecastConditions(
    @Json(name = "list") val listOfForecasts: List<ForecastItem>,
)

data class ForecastItem(
    @Json(name = "weather") val weatherData: List<WeatherData>,
    @Json(name = "dt") val date: Long,
    @Json(name = "sunrise") val sunrise: Long,
    @Json(name = "sunset") val sunset: Long,
    @Json(name = "temp") val temp: Temperature,
    @Json(name = "pressure") val pressure: Int,
    @Json(name = "humidity") val humidity: Int,
)

data class Temperature(
    @Json(name = "day") val day: Float,
    @Json(name = "min") val min: Float,
    @Json(name = "max") val max: Float,
)