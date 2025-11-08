package com.example.practico3.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.practico3.TripViewModel
import com.example.practico3.data.Lugar

@Composable
fun PlaceDetailScreen(placeId: Int?, navController: NavController, vm: TripViewModel) {

    if (placeId != null) {
        vm.fetchPlaceById(placeId)
        val place by vm.place.collectAsState()

        Scaffold { innerPadding ->
            Box(Modifier.fillMaxSize().padding(innerPadding).padding(15.dp)){
                Column() {

                    Text(text = place?.name ?: "Lugar sin nombre", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(5.dp))
                    Text(text = place?.description ?: "Lugar sin descripcion", fontSize = 20.sp)
                    Spacer(Modifier.height(15.dp))
                    Text(text = "Ciudad:", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    Spacer(Modifier.height(5.dp))
                    Text(text = place?.city ?: "Lugar sin ciudad")
                    Spacer(Modifier.height(15.dp))
                    Text(text = "Direcciones:", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    Spacer(Modifier.height(5.dp))
                    Text(text = place?.directions ?: "Lugar sin direcciones")
                    Spacer(Modifier.height(15.dp))
                    Text(text = "Tiempo:", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    Spacer(Modifier.height(5.dp))
                    Text(text = place?.time_to_spend ?: "Lugar sin direcciones")
                    Spacer(Modifier.height(15.dp))
                    Text(text = "Precio:", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    Spacer(Modifier.height(5.dp))
                    Text(text = place?.price ?: "Lugar sin direcciones")
                    Spacer(Modifier.height(15.dp))
                    Text(text = "Imagen:", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    Spacer(Modifier.height(15.dp))
                    Row (horizontalArrangement = Arrangement.Center ,modifier = Modifier.fillMaxWidth()) {
                        if (place?.image_url?.isNotEmpty() == true){
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current).data(place?.image_url).crossfade(true).build(),
                                contentDescription = "Imagend del lugar",
                                modifier = Modifier.size(200.dp).background(Color.Gray),
                                contentScale = ContentScale.Crop
                            )
                        }else{
                            Box(Modifier.size(200.dp).background(Color.Gray))
                        }
                    }
                }

                Button(onClick = {
                    navController.navigate("tripDetail/${place?.trip_id}")

                } ,modifier = Modifier.align(Alignment.BottomStart)) {
                    Text(text = "Volver", color = Color.White)
                }
            }
        }
    }
    else
    {
        Text("No hay un id definido")
    }
}


@Preview
@Composable
fun PreviewPlaceDetail(){

    val place = Lugar(1, "Universal inslands of adventure", city = "Orlando", description = "Parque de diversiones chido", "https://blog.discoveruniversal.com/wp-content/uploads/2017/12/Universals-Islands-of-Adventure.jpg", directions = "Tomar el bus a universal en cualquier hotel", time_to_spend = "8 horas", price = "100 bs por persona", trip_id = 2, created_at = "", updated_at = "")


    Box(Modifier.fillMaxSize().padding(15.dp, top = 25.dp)){
        Column(Modifier.fillMaxWidth().background(Color.White).padding(15.dp, top = 25.dp)) {
            Text(text = place?.name ?: "Lugar sin nombre", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(5.dp))
            Text(text = place?.description ?: "Lugar sin descripcion", fontSize = 20.sp)
            Spacer(Modifier.height(20.dp))
            Text(text = "Ciudad:", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Spacer(Modifier.height(5.dp))
            Text(text = place?.city ?: "Lugar sin ciudad")
            Spacer(Modifier.height(15.dp))
            Text(text = "Direcciones:", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Spacer(Modifier.height(5.dp))
            Text(text = place?.directions ?: "Lugar sin direcciones")
            Spacer(Modifier.height(15.dp))
            Text(text = "Tiempo:", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Spacer(Modifier.height(5.dp))
            Text(text = place?.time_to_spend ?: "Lugar sin direcciones")
            Spacer(Modifier.height(15.dp))
            Text(text = "Precio:", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Spacer(Modifier.height(5.dp))
            Text(text = place?.price ?: "Lugar sin direcciones")
            Spacer(Modifier.height(15.dp))
            Text(text = "Imagen:", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Spacer(Modifier.height(5.dp))
            Row (horizontalArrangement = Arrangement.Center ,modifier = Modifier.fillMaxWidth()) {
                if (place.image_url != null){
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(place.image_url).crossfade(true).build(),
                        contentDescription = "Imagend del lugar",
                        modifier = Modifier.size(200.dp).background(Color.Gray),
                        contentScale = ContentScale.Crop

                    )
                }else{
                    Box(Modifier.size(200.dp).background(Color.Gray))
                }
            }



        }
    }
}