package com.example.practico4

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practico3.api.RetrofitClient
import com.example.practico4.data.Punto
import com.example.practico4.data.Ruta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RoutesViewModel: ViewModel() {

    private val _routes = MutableStateFlow<List<Ruta>>(emptyList())
    val routes = _routes.asStateFlow()


    private val _points = MutableStateFlow<List<Punto>>(emptyList())
    val points = _points.asStateFlow()


    var username: String? = null


    fun fetchUserRoutes() = viewModelScope.launch {
        if (username.isNullOrBlank())return@launch
        try {
            _routes.value = emptyList()
            val response = RetrofitClient.api.getUserRoutes(username!!)
            _routes.value = response
        }
        catch (e: HttpException){
            Log.e("Http error", e.message.toString())
            _routes.value = emptyList()
        }
        catch (e: Exception){
            Log.e("Error", e.message.toString())
            _routes.value = emptyList()
        }
    }

    fun insertRoute(route:Ruta) = viewModelScope.launch {
        try {
            val response = RetrofitClient.api.postRoute(route)
        }
        catch (e: HttpException){
            Log.e("Http error", e.message.toString())
        }
        catch (e: Exception){
            Log.e("Error", e.message.toString())
        }
    }


    fun fetchRoutePoints(routeId: Int) = viewModelScope.launch {
        try {
            _points.value = emptyList()
            val response = RetrofitClient.api.getRoutePoints(routeId)
            Log.d("pointsFetched", response.toString())
            _points.value = response
        }
        catch (e: HttpException){
            Log.e("Http error", e.message.toString())
            _points.value = emptyList()
        }
        catch (e: Exception){
            Log.e("Error", e.message.toString())
            _points.value = emptyList()
        }
    }

    fun insertPoint(point: Punto) = viewModelScope.launch {
        try {
            val currentPoints  = _points.value.toMutableList()
            currentPoints.add(point)
            _points.value = currentPoints

            val response = RetrofitClient.api.postPoint(point)
            Log.d("Inserted Point", response.toString())



        }
        catch (e: HttpException){
            Log.e("Http error", e.message.toString())
        }
        catch (e: Exception){
            Log.e("Error", e.message.toString())
        }
    }

    fun deletePoint(id: Int) = viewModelScope.launch {
        try {

            _points.value = _points.value.filter { it.id != id }
            val response = RetrofitClient.api.deletePoint(id)

        }
        catch (e: Exception) {
            Log.e("DeleteError", e.message.toString())
        }
    }

    fun deleteRoute(id:Int) = viewModelScope.launch {
        try {

            _routes.value = _routes.value.filter { it.id != id }
            val response = RetrofitClient.api.deleteRoute(id)

        }
        catch (e: Exception) {
            Log.e("DeleteError", e.message.toString())
        }
    }



}