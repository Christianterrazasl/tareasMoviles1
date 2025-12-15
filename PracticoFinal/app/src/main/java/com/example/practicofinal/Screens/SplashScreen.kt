package com.example.practicofinal.Screens

import android.util.Log
import android.window.SplashScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practicofinal.TokenManager
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){

    val context = navController.context
    val session = remember { TokenManager(context) }

    LaunchedEffect(true) {
        delay(3000)
        val token = session.getToken()
        Log.d("Token", token.toString())
        if(token.isNullOrEmpty()){
            navController.navigate("login"){
                popUpTo("splash"){ inclusive = true }
            }
        }
        else{
            navController.navigate("home"){
                popUpTo("splash"){ inclusive = true }
            }
        }
    }

    Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center ,modifier = Modifier.fillMaxSize().padding(14.dp)) {
        Text("App de dueños")
        Text(text = "Paseo de perros", fontSize = 25.sp, fontWeight = FontWeight.Light)
    }
}