package com.example.jornadas.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class GeocodingResponse(val results: List<GeocodingResult>?)
data class GeocodingResult(val latitude: Double, val longitude: Double)

data class WeatherResponse(val current_weather: CurrentWeather)
data class CurrentWeather(val temperature: Double)


interface WeatherApi {
    @GET("https://geocoding-api.open-meteo.com/v1/search")
    suspend fun getCoordinates(
        @Query("name") cityName: String,
        @Query("count") count: Int = 1,
        @Query("language") language: String = "pt"
    ): GeocodingResponse

    @GET("https://api.open-meteo.com/v1/forecast")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lng: Double,
        @Query("current_weather") current: Boolean = true
    ): WeatherResponse
}

object WeatherClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: WeatherApi = retrofit.create(WeatherApi::class.java)
}