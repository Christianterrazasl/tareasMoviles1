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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.practico3.TripViewModel
import com.example.practico3.data.Lugar

@Composable
fun TripDetailScreen(tripId: Int?, vm: TripViewModel, navController: NavController) {

    val trips by vm.trips.collectAsState()
    val trip = trips.find { it.id == tripId }

    if (tripId != null && trip != null) {
        LaunchedEffect(tripId) {
            vm.fetchPlaces(tripId)
        }
        val places by vm.places.collectAsState()
        val isDeletable = vm.username!!.lowercase() == trip.username!!.lowercase()

        Scaffold { innerPadding ->
            Box(Modifier.fillMaxSize().padding(innerPadding)){
                Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween){
                    Text(text = "Viaje: " + trip.name, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 15.dp, vertical = 20.dp))
                    if (places.isNotEmpty()){
                        LazyColumn (Modifier.fillMaxWidth()) {
                            items(places){
                                    place ->
                                PlaceItem(place, navController, isDeletable, vm)
                            }
                        }
                    }else{
                        Text(text = "No hay lugares para mostrar", modifier = Modifier.padding(10.dp))
                    }

                    Column {
                        if(trip.username.lowercase() == vm.username!!.lowercase()){
                            Button(onClick = { navController.navigate("createPlace/${trip.id}") } ,modifier = Modifier.padding(15.dp)) {
                                Text("Agregar Lugar")
                            }
                            Button(onClick = {

                                vm.deleteTrip(trip.id!!)
                                navController.navigate("myTrips") }


                                ,modifier = Modifier.padding(15.dp)) {
                                Text("Eliminar Viaje")
                            }

                            Button(onClick = {

                                vm.fetchUserTrips()
                                navController.navigate("myTrips") } ,

                                modifier = Modifier.padding(15.dp)) {
                                Text("Volver")
                            }

                        }else {

                            Button(onClick = { navController.navigate("home") },
                                modifier = Modifier.padding(15.dp)) {
                                Text("Volver")
                            }
                        }

                    }
                }


            }
        }
    }
    else{
        Text("Error: Id no es valido")
    }
}

@Composable
fun PlaceItem(place: Lugar, navController: NavController, isDeletable: Boolean, vm: TripViewModel){
    Row (horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier.fillMaxWidth().background(Color.Gray).padding(15.dp).clickable{
        navController.navigate("placeDetail/${place.id}")
    }) {
        if (!place.image_url.isNullOrEmpty()){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(place.image_url).crossfade(true).build(),
                contentDescription = "Imagend del lugar",
                modifier = Modifier.size(80.dp).background(Color.Gray),
                contentScale = ContentScale.Crop
            )
        }else{
            Box(Modifier.size(80.dp).background(Color.Gray))
        }

        Column (horizontalAlignment = Alignment.End) {
            Text(textAlign = TextAlign.End ,text = place.name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(textAlign = TextAlign.End ,text = place.city, color = Color.White, fontSize = 15.sp)
        }

        if (isDeletable){
            Button(
                onClick = {
                    vm.deletePlace(place.id!!)
                }
            ) { Text("Eliminar") }
        }
    }
    Spacer(Modifier.height(5.dp))
}