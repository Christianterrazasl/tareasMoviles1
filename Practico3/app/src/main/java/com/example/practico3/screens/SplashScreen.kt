package com.example.practico3.screens

import android.window.SplashScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practico3.TripViewModel

@Composable
fun SplashScreen(navController: NavController, vm: TripViewModel){

    var username by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ,modifier = Modifier.fillMaxSize().padding(innerPadding)) {

            Text(text = "Ingrese su nombre de usuario:")
            OutlinedTextField(
                value = username,
                onValueChange = {username = it},
                label = { Text("Username")}
            )
            Spacer(Modifier.height(10.dp))
            Button(onClick = {
                vm.username = username
                navController.navigate("home")
            }){
                Text(text = "Ingresar")
            }
        }
    }
}