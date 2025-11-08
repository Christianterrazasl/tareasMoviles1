package com.example.practico3.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practico3.TripViewModel
import com.example.practico3.api.RetrofitClient
import com.example.practico3.data.Viaje

@Composable
fun HomeScreen(navController: NavController,vm: TripViewModel) {

    vm.fetchTrips()
    val trips by vm.trips.collectAsState()

    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)){
            Column{

                Text(text = "Viajes", fontWeight = FontWeight.Bold, fontSize = 25.sp, modifier = Modifier.padding(15.dp))
                TripsList(trips, navController)
            }

            Button(onClick = {
                navController.navigate("myTrips")
            },
                modifier = Modifier.align(Alignment.BottomEnd).padding(15.dp)
                ) {
                Text(text = "Mis Viajes")
            }
        }
    }
}

@Composable
fun TripsList(trips:List<Viaje>, navController: NavController){
    LazyColumn {
        items(trips){ trip ->
            TripItem(trip, navController)
            Spacer(Modifier.height(3.dp))
        }
    }
}

@Composable
fun TripItem(trip: Viaje, navController: NavController){
    Column(modifier = Modifier.fillMaxWidth().background(color = Color.Black).padding(12.dp).clickable{
        navController.navigate("tripDetail/${trip.id}")
    }) {
        Row (verticalAlignment = Alignment.CenterVertically ,horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = trip.name ?: "Viaje sin nombre", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = trip.country ?: "Pais Desconocido")
        }
        Spacer(Modifier.height(5.dp))
        Row {
            Text(text = trip.username ?: "")
        }
    }
}