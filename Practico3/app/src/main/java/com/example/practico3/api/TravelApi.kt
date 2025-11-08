package com.example.practico3.api
import com.example.practico3.data.Viaje
import retrofit2.http.GET

interface TravelApi {
    @GET("trips")
    suspend fun getTrips(): List<Viaje>
}