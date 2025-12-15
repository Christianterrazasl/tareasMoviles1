package com.example.practicofinal.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practicofinal.PaseoViewModel

@Composable
fun HomeScreen(navController: NavController, vm: PaseoViewModel) {
    Box(Modifier.fillMaxSize()) {
        Button(
            onClick = {
            vm.logout()
            navController.navigate("login")
                      },
            modifier = Modifier.align(Alignment.TopEnd).padding(15.dp)){
            Text("Logout")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally ,verticalArrangement = Arrangement.Center ,modifier = Modifier.fillMaxSize()) {
            Button(onClick = {
                navController.navigate("mascotas")
            }) {
                Text("Mis Mascotas")
            }

            Spacer(Modifier.height(14.dp))

            Button(onClick = {
                navController.navigate("paseos")
            }) {
                Text("Paseos")
            }
        }

    }
}