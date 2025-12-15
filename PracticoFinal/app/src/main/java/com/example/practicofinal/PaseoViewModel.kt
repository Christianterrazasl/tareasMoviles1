package com.example.practicofinal

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicofinal.api.RetrofitClient
import com.example.practicofinal.data.LoginRequest
import com.example.practicofinal.data.MascotaReq
import com.example.practicofinal.data.MascotaRes
import com.example.practicofinal.data.RegisterReq
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PaseoViewModel(application: Application): AndroidViewModel(application) {

    private var _mascotas = MutableStateFlow<List<MascotaRes>>(emptyList())
    val mascotas = _mascotas.asStateFlow()

    private val session = TokenManager(application)

    var loginSuccess by mutableStateOf(false)
        private set


    fun login(email: String, password: String) = viewModelScope.launch {
         try {
            val response = RetrofitClient.api.login(LoginRequest(email, password))
             Log.d("Login response", response.toString())
            session.saveToken(response.access_token)
             loginSuccess = true
        }
        catch (err: Exception){
            Log.d("Error", err.message.toString())
            loginSuccess = false
        }
    }

    fun logout() {
        session.clearToken()
        loginSuccess = false
    }

    fun hasToken(): Boolean {
        return !session.getToken().isNullOrEmpty()
    }

    fun register(name:String, email: String, password: String) = viewModelScope.launch {
        try {
            val response = RetrofitClient.api.register(RegisterReq(name = name, email = email, password = password))
            Log.d("register response", response.toString())
        }
        catch (err: Exception){
            Log.d("register error", err.message.toString())
        }
    }

    fun fetchMascotas() = viewModelScope.launch {
        try {
            _mascotas.value = emptyList()
            val tokenString = "Bearer ${session.getToken()}"
            val response = RetrofitClient.api.getMascotas(tokenString)
            _mascotas.value = response
        }
        catch (err: Exception){
            Log.d("Error fetching mascotas", err.toString())
        }
    }

    fun deleteMascota(id:Int) = viewModelScope.launch {
        try {
            _mascotas.value = _mascotas.value.filter { it.id != id }
            val tokenString = "Bearer ${session.getToken()}"
            RetrofitClient.api.deleteMascota(tokenString, id)
        }
        catch (err: Exception){
            Log.d("Error deleting mascota", err.toString())
        }

    }

    fun createMascota(mascota: MascotaReq) = viewModelScope.launch{
        try {
            val tokenString = "Bearer ${session.getToken()}"
            RetrofitClient.api.postMascota(tokenString, mascota)
        }
        catch (err: Exception){
            Log.d("Error creating mascota", err.toString())
        }

    }


}