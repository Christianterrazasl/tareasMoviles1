package com.example.practico3.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practico3.TripViewModel
import com.example.practico3.data.Lugar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CreatePlaceScreen(tripId:Int?, navController: NavController, vm: TripViewModel){
    Scaffold { innerPadding ->

        LaunchedEffect(Unit) {
            vm.fetchTrips()
        }

        val scope = rememberCoroutineScope()

        val trips by vm.trips.collectAsState()
        val trip = trips.find { it.id == tripId }

        var nombre by remember { mutableStateOf("") }
        var ciudad by remember { mutableStateOf("") }
        var descripcion by remember { mutableStateOf("") }
        var direcciones by remember { mutableStateOf("") }
        var tiempo by remember { mutableStateOf("") }
        var precio by remember { mutableStateOf("") }
        var imageUrl by remember { mutableStateOf("") }

        var error by remember { mutableStateOf(false) }

        if(trip != null){
            Column(Modifier.padding(innerPadding).padding(15.dp)) {
                Text(text = "Agregar lugar", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(15.dp))
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre del lugar") }
                )
                Spacer(Modifier.height(15.dp))
                OutlinedTextField(
                    value = ciudad,
                    onValueChange = { ciudad = it },
                    label = { Text("Ciudad") }
                )
                Spacer(Modifier.height(15.dp))
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripcion") }
                )
                Spacer(Modifier.height(15.dp))
                OutlinedTextField(
                    value = direcciones,
                    onValueChange = { direcciones = it },
                    label = { Text("Direcciones") }
                )
                Spacer(Modifier.height(15.dp))
                OutlinedTextField(
                    value = tiempo,
                    onValueChange = { tiempo = it },
                    label = { Text("Tiempo de viaje") }
                )
                Spacer(Modifier.height(15.dp))
                OutlinedTextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = { Text("Precio") }
                )
                Spacer(Modifier.height(15.dp))
                OutlinedTextField(
                    value = imageUrl,
                    onValueChange = { imageUrl = it },
                    label = { Text("Url de la imagen") }
                )
                Spacer(Modifier.height(15.dp))
                Button(onClick = {
                    if (nombre.isBlank() || ciudad.isBlank() || descripcion.isBlank() || direcciones.isBlank() || tiempo.isBlank() || precio.isBlank() || imageUrl.isBlank()){
                        error = true
                    }else{
                        vm.postPlace(Lugar(name = nombre, city = ciudad, description = descripcion, time_to_spend = tiempo, price = precio, directions = direcciones, image_url = imageUrl, created_at = null, updated_at = null, trip_id = trip.id!!, id = null))

                        scope.launch{
                            delay(800)
                            navController.navigate("tripDetail/${trip.id}")
                        }

                    }


                }) {
                    Text(text = "Agregar lugar")
                }
                Spacer(Modifier.height(15.dp))
                Button(onClick = {

                    navController.navigate("tripDetail/${trip.id}")
                }) {
                    Text(text = "Cancelar")
                }

                Spacer(Modifier.height(15.dp))
                if (error){
                    Text("Completa todos los campos antes de agregar lugar")
                }
            }
        }
        else{
            Text(modifier = Modifier.padding(15.dp),text = "No hay un id valido")
        }
    }

}