package com.example.practico4.Screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practico4.RoutesViewModel
import com.example.practico4.data.Ruta

@Composable
fun CreateRouteScreen(navController: NavController, vm: RoutesViewModel){

    var name by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(15.dp)) {
            Text(text = "Crear ruta", fontSize =25.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(15.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre")}
            )
            Spacer(Modifier.height(15.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    onClick = {
                        navController.navigate("routes")
                    }
                ) {Text("Cancelar") }

                Button(
                    onClick = {
                        vm.insertRoute(Ruta(id = null, name = name, username = vm.username!!))
                        navController.navigate("routes")
                    }
                ) {Text("OK") }


            }

        }
    }

}