package com.example.practico3.screens

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.memory.MemoryCache
import com.example.practico3.TripViewModel
import com.example.practico3.data.Viaje
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CreateTripScreen(navController: NavController, vm: TripViewModel) {

    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Scaffold { innerPadding ->
        Column (Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(15.dp)){
            Text(text = "Crear viaje", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(15.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del viaje") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(15.dp))
            OutlinedTextField(
                value = pais,
                onValueChange = { pais = it },
                label = { Text("Pais del viaje") },
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(Modifier.height(15.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        if (nombre.isBlank() || pais.isBlank()){
                            error = true
                        }
                        else{
                            vm.postTrip(
                                Viaje(
                                    name = nombre,
                                    country = pais,
                                    username = vm.username,
                                    created_at = null,
                                    updated_at = null,
                                    id = null
                                )
                            )

                            scope.launch{
                                delay(800)
                                navController.navigate("myTrips")

                            }
                        }
                    }
                ) { Text("Crear viaje") }

                Button(
                    onClick = {
                            navController.navigate("myTrips")
                    }
                ) { Text("Cancelar") }
            }
            Spacer(Modifier.height(15.dp))
            if (error){
                Text("Completa todos los campos antes de crear viaje")
            }

        }
    }
}

@Preview
@Composable
fun CreateTripPreview(){

    var nombre by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("") }

    nombre = "Viaje a bahamas"
    pais = "Estados unidos"

    Column (Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(15.dp)){
        Text(text = "Crear viaje", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(15.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del viaje") },
            placeholder = { Text("Text de placeholder") }
        )

    }
}