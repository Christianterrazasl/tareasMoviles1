package com.example.practico4.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practico4.R
import com.example.practico4.RoutesViewModel
import com.example.practico4.data.Punto
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(routeId:Int, navController: NavController, vm: RoutesViewModel){

    val points by vm.points.collectAsState()

    var selectedPoint by remember { mutableStateOf<Punto?>(null) }
    var showDialog by remember { mutableStateOf(false) }



    LaunchedEffect(Unit) {
        vm.fetchRoutePoints(routeId)
    }

    val cameraPositionState = rememberCameraPositionState(){
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 14f)
    }

    if (points.isNotEmpty()){
        val firstPoint = points.first()
        LaunchedEffect(firstPoint) {
            val position = LatLng(firstPoint.latitude.toDouble(), firstPoint.longitude.toDouble())
            cameraPositionState.position = CameraPosition.fromLatLngZoom(position, 14f)
        }
    }


    Scaffold { innerPadding ->

        Box(Modifier.fillMaxSize().padding(innerPadding)) {

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ){
                points.forEach { point ->
                    Marker(
                        state = MarkerState(LatLng(point.latitude.toDouble(), point.longitude.toDouble())),
                        onClick = {
                            selectedPoint = point
                            showDialog = true
                            true
                        }

                    )
                }

                if (points.size > 1) {
                    Polyline(points = points.map { LatLng(it.latitude.toDouble(), it.longitude.toDouble()) })
                }
            }

            Image(
                painter = painterResource(id = R.drawable.locationpin),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center).size(45.dp)
            )

            Button(
                onClick = {
                    val centerPosition = cameraPositionState.position.target
                    val punto = Punto(id = null, latitude = centerPosition.latitude.toString(), longitude = centerPosition.longitude.toString(), route_id = routeId)
                    Log.d("Punto", punto.toString())
                   vm.insertPoint(punto)


                },
                modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
            ) {
                Text("Agregar")
            }

            Button(
                onClick = {
                    navController.navigate("routes")
                },
                modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)
            ) {
                Text("Volver")
            }


            if (showDialog && selectedPoint != null) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Eliminar punto") },
                    text = { Text("¿Deseas eliminar este punto?") },
                    confirmButton = {
                        Button(onClick = {
                            vm.deletePoint(selectedPoint!!.id!!)
                            showDialog = false
                        }) {
                            Text("Eliminar")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("Cancelar")
                        }
                    }
                )
            }

        }
    }


}