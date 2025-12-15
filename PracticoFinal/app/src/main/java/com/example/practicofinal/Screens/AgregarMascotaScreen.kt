package com.example.practicofinal.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practicofinal.PaseoViewModel
import com.example.practicofinal.data.MascotaReq
import kotlinx.coroutines.delay

@Composable
fun AgregarMascotaScreen(navController: NavController, vm: PaseoViewModel){

    var nombre by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(14.dp)) {
        Text("Agregar mascota", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = { Text("Nombre")}
        )
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            value = especie,
            onValueChange = {especie = it},
            label = { Text("Especie")}
        )
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            value = notas,
            onValueChange = {notas = it},
            label = { Text("Notas")}
        )
        Spacer(Modifier.height(4.dp))
        Button(onClick = {

            vm.createMascota(MascotaReq(nombre, especie, notas, null))
            navController.navigate("mascotas")


        }) { Text("Crear")}
        Spacer(Modifier.height(4.dp))
        Button(onClick = {
            navController.navigate("mascotas")
        }) { Text("Cancelar")}
    }
}