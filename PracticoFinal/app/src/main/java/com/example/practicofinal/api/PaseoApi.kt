package com.example.practicofinal.api

import com.example.practicofinal.data.LoginRequest
import com.example.practicofinal.data.LoginResponse
import com.example.practicofinal.data.MascotaReq
import com.example.practicofinal.data.MascotaRes
import com.example.practicofinal.data.RegisterReq
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PaseoApi {

    @POST("auth/clientlogin")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/clientregister")
    suspend fun register(@Body request: RegisterReq)

    @GET("pets")
    suspend fun getMascotas(@Header("Authorization") token:String) : List<MascotaRes>

    @DELETE("pets/{id}")
    suspend fun deleteMascota(@Header("Authorization") token:String, @Path("id") id:Int)

    @POST("pets")
    suspend fun postMascota(@Header("Authorization") token:String, @Body mascota: MascotaReq)

}