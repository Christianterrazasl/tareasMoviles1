package com.example.practico3.api
import com.example.practico3.data.Lugar
import com.example.practico3.data.Viaje
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TravelApi {
    @GET("trips/")
    suspend fun getTrips(): List<Viaje>

    @GET("trips/{tripId}/places")
    suspend fun getPlaces(@Path("tripId") tripId: Int): List<Lugar>

    @GET("trips/{username}")
    suspend fun getUserTrips(@Path("username") username: String): List<Viaje>

    @GET("places/{placeId}")
    suspend fun getPlace(@Path("placeId") placeId: Int): Lugar

    @POST("trips")
    suspend fun postTrip(@Body trip: Viaje): Response<Viaje>

    @POST("places")
    suspend fun postPlace(@Body place:Lugar): Response<Lugar>

    @DELETE("places/{placeId}")
    suspend fun deletePlace(@Path("placeId") placeId: Int):Response<Lugar>

    @DELETE("trips/{tripId}")
    suspend fun deleteTrip(@Path("tripId") tripId: Int):Response<Viaje>



}