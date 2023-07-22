package com.example.weatherapp

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/weather")
    suspend fun getCurrentData (
        @Query("zip") zip: String = "55346",
        @Query("appid") appID: String = "2f990f18c1f32a968bc2bb1dac2da4e8",
        @Query("units") units: String = "imperial",
    ) : CurrentConditions

    @GET("data/2.5/forecast/daily")
    suspend fun getForecastData (
        @Query("zip") zip: String = "55346",
        @Query("appid") appID: String = "2f990f18c1f32a968bc2bb1dac2da4e8",
        @Query("units") units: String = "imperial",
        @Query("cnt") count: Int = 16,
    ) : ForecastConditions
}