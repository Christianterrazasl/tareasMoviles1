package com.example.practico3

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practico3.api.RetrofitClient
import com.example.practico3.data.Lugar
import com.example.practico3.data.Viaje
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TripViewModel: ViewModel() {
    private val _trips = MutableStateFlow<List<Viaje>>(emptyList())
    val trips: StateFlow<List<Viaje>> = _trips.asStateFlow()
    private val _places = MutableStateFlow<List<Lugar>>(emptyList())
    val places: StateFlow<List<Lugar>> = _places.asStateFlow()

    private val _place = MutableStateFlow<Lugar?>(null)
    val place: StateFlow<Lugar?> = _place.asStateFlow()

    var toastMessage:String? = null


    var username:String? = null

    fun fetchTrips() = viewModelScope.launch {
        try {
            val response = RetrofitClient.api.getTrips()
            _trips.value = response
        } catch (e: HttpException) {
            Log.e("TripViewModel", "HTTP error: ${e.code()} ${e.message()}")
            _trips.value = emptyList()
        } catch (e: Exception) {
            Log.e("TripViewModel", "Unknown error", e)
            _trips.value = emptyList()
        }
    }

    fun fetchPlaces(tripId:Int) = viewModelScope.launch{
        try {
            val response = RetrofitClient.api.getPlaces(tripId)
            _places.value = response
        }
        catch (e:HttpException){
            Log.e("TripViewModel", "HTTP error: ${e.code()} ${e.message()}")
            _places.value = emptyList()
        }
        catch (e:Exception){
            Log.e("TripViewModel", "Unknown error", e)
            _places.value = emptyList()
        }
    }

    fun fetchUserTrips() = viewModelScope.launch {
        if (username.isNullOrEmpty()){
            _trips.value = emptyList()
        }else{
        try {
            val response = RetrofitClient.api.getUserTrips(username!!)
            _trips.value = response
        }catch (e: HttpException) {
            Log.e("TripViewModel", "HTTP error: ${e.code()} ${e.message()}")
            _trips.value = emptyList()
        } catch (e: Exception) {
            Log.e("TripViewModel", "Unknown error", e)
            _trips.value = emptyList()
        }

        }
    }

    fun fetchPlaceById(placeId:Int) = viewModelScope.launch {
        try {
            val response = RetrofitClient.api.getPlace(placeId)
            _place.value = response
        } catch (e: HttpException) {
            Log.e("TripViewModel", "HTTP error: ${e.code()} ${e.message()}")
            _place.value = null
        } catch (e: Exception) {
            Log.e("TripViewModel", "Unknown error", e)
            _place.value = null
        }
    }

    fun postTrip(trip:Viaje)= viewModelScope.launch {
        try {
            val response = RetrofitClient.api.postTrip(trip)
            toastMessage = if (response.isSuccessful){
                "Viaje creado correctamente"
            } else{
                "Error al crear viaje"
            }
        }
        catch (e: HttpException){
            toastMessage = "Error al crear viaje"
            Log.d("Error",e.message().toString())
        }
        catch (e: Exception){
            toastMessage = "Error al crear viaje"
            Log.d("Error",e.toString())

        }
    }

    fun postPlace(place:Lugar)= viewModelScope.launch {
        try {
            val response = RetrofitClient.api.postPlace(place)
            toastMessage = if (response.isSuccessful){
                "Lugar agregado correctamente"
            } else{
                "Error al agregar lugar"
            }
        }
        catch (e: HttpException){
            toastMessage = "Error al agregar lugar"
            Log.d("Error",e.message().toString())
        }
        catch (e: Exception){
            toastMessage = "Error al agregar lugar"
            Log.d("Error",e.toString())

        }
    }

    fun deletePlace(placeId: Int){

        _places.value = _places.value.filterNot { it.id == placeId }

        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.deletePlace(placeId)
                toastMessage = if (response.isSuccessful){
                    "Lugar eliminado correctamente"
                } else{
                    "Error al eliminar lugar"
                }
            }
            catch (e: HttpException){
                toastMessage = "Error al eliminar lugar"
                Log.d("Error",e.message().toString())
            }
            catch (e: Exception){
                toastMessage = "Error al eliminar lugar"
                Log.d("Error",e.toString())

            }
        }
    }

    fun deleteTrip(tripId: Int)= viewModelScope.launch {
        try {
            val response = RetrofitClient.api.deleteTrip(tripId)
            toastMessage = if (response.isSuccessful){
                "Viaje eliminado correctamente"
            } else{
                "Error al eliminar viaje"
            }
        }
        catch (e: HttpException){
            toastMessage = "Error al eliminar viaje"
            Log.d("Error",e.message().toString())
        }
        catch (e: Exception){
            toastMessage = "Error al eliminar viaje"
            Log.d("Error",e.toString())

        }
    }


}

