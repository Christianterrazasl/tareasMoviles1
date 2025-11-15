package com.example.practico4.Screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practico4.RoutesViewModel

@Composable
fun RoutesScreen(navController: NavController, vm: RoutesViewModel){

    val routes by vm.routes.collectAsState()

    LaunchedEffect(Unit) {
        vm.fetchUserRoutes()
    }

    Scaffold { innerPadding ->
        Box(Modifier.fillMaxSize().padding(innerPadding).padding(15.dp)) {

            Column(Modifier.fillMaxSize()) {
                Text(text = "Mis rutas", fontSize = 25.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(15.dp))

                if (routes.isEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Cargando rutas ...")
                    }
                } else {
                    LazyColumn {
                        items(routes) { route ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth().background(
                                    color = Color.DarkGray,
                                    shape = RoundedCornerShape(8.dp)
                                ).padding(15.dp).clickable {
                                    navController.navigate("map/${route.id.toString()}")
                                }
                            ) {
                                Text(text = route.name, color = Color.White)
                                Button(
                                    onClick = {
                                        vm.deleteRoute(route.id!!)
                                    }
                                ) { Text(text = "x", fontSize = 20.sp)}
                            }
                            Spacer(Modifier.height(5.dp))
                        }
                    }
                }
            }

            Button(
                onClick = { navController.navigate("createRoute") },
                modifier = Modifier.align(Alignment.BottomEnd)

            ) {
                Text(text = "Crear ruta")
            }

            Button(
                onClick = { vm.fetchUserRoutes() },
                modifier = Modifier.align(Alignment.BottomStart)

            ) {
                Text(text = "Recargar")
            }
        }
    }

}