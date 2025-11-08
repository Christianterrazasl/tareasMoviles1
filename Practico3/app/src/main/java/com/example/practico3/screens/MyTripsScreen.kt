package com.example.practico3.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practico3.TripViewModel

@Composable
fun MyTripsScreen(navController: NavController, vm: TripViewModel){


    val trips by vm.trips.collectAsState()
    LaunchedEffect(Unit) {
        vm.fetchUserTrips()
    }

    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)){
            Column{
                Text(text = "Mis Viajes", fontWeight = FontWeight.Bold, fontSize = 25.sp, modifier = Modifier.padding(15.dp))
                if(trips.isNotEmpty()){

                    TripsList(trips, navController)
                }
                else {
                    Text(text = "No hay viajes para mostrar", modifier = Modifier.padding(15.dp))
                }
            }

            Button(onClick = {
                navController.navigate("home")
            },
                modifier = Modifier.align(Alignment.BottomStart).padding(15.dp)
            ) {
                Text(text = "Volver")
            }

            Button(onClick = {
                navController.navigate("createTrip")
            },
                modifier = Modifier.align(Alignment.BottomEnd).padding(15.dp)
            ) {
                Text(text = "Crear viaje")
            }
        }
    }
}