package com.example.practico3.api

import com.example.practico4.data.Punto
import com.example.practico4.data.Ruta
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RoutesApi {

    //rutas (no es ia esto)

    @GET("routes")
    suspend fun getRoutes(): List<Ruta>

    @GET("routes/{username}")
    suspend fun getUserRoutes(@Path("username") username:String): List<Ruta>

    @POST("routes")
    suspend fun postRoute(@Body route: Ruta): Response<Ruta>

    @DELETE("routes/{routeId}")
    suspend fun deleteRoute(@Path("routeId") routeId:Int): Response<Void>


    //puntos (tampoco)

    @GET("routes/{routeId}/locations")
    suspend fun getRoutePoints(@Path("routeId") routeId:Int) : List<Punto>

    @POST("locations")
    suspend fun postPoint(@Body point: Punto): Response<Punto>

    @DELETE("locations/{pointId}")
    suspend fun deletePoint(@Path("pointId") pointId:Int): Response<Void>
}