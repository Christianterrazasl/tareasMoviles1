package com.example.practicofinal.Screens

import android.app.Application
import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practicofinal.PaseoViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.practicofinal.data.MascotaReq
import com.example.practicofinal.data.MascotaRes

@Composable
fun MascotasScreen (navController: NavController, vm : PaseoViewModel){

    LaunchedEffect(Unit) {
        vm.fetchMascotas()
    }

    val mascotas = vm.mascotas.collectAsState()

    Box(Modifier.fillMaxSize().padding(14.dp)){
        Column (Modifier.fillMaxSize()){
            Text("Mis mascotas", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(14.dp))
            if (!mascotas.value.isNullOrEmpty()){
                LazyColumn {
                    items(mascotas.value){ mascota ->
                        MascotaItem(mascota, vm)
                        Spacer(Modifier.height(4.dp))
                    }
                }
            }
            else{
                Row(verticalAlignment = Alignment.CenterVertically ,horizontalArrangement = Arrangement.Center ,modifier = Modifier.fillMaxSize()) {
                    Text("Cargando ...")
                }
            }
        }
        Button(onClick = {navController.navigate("home")} ,modifier = Modifier.align(Alignment.BottomStart)) { Text("Volver a incio") }
        Button(onClick = {navController.navigate("agregarMascota")} ,modifier = Modifier.align(Alignment.BottomEnd)) { Text("Agregar mascota") }

    }
}

@Composable
fun MascotaItem(mascota: MascotaRes, vm: PaseoViewModel){
    Row(Modifier.fillMaxWidth().background(color = Color.LightGray).padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {

        Row (){

            if (!mascota.photoUrl.isNullOrEmpty()){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(mascota.photoUrl).crossfade(true).build(),
                    contentDescription = "Imagend del lugar",
                    modifier = Modifier.size(80.dp).background(Color.Gray),
                    contentScale = ContentScale.Crop
                )
            }else{
                Box(Modifier.size(80.dp).background(Color.Gray))
            }
            Spacer(Modifier.width(14.dp))
            Column {
                Text(text = mascota.name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(4.dp))
                Text(text = mascota.species, color = Color.White, fontSize = 18.sp)
                Spacer(Modifier.height(8.dp))
                Text(text = mascota.notes, color = Color.White, fontSize = 16.sp)



            }
        }

        Text(text = "x", fontSize = 25.sp, color = Color.White, modifier = Modifier.clickable{

            vm.deleteMascota(mascota.id)

        })



    }
}

