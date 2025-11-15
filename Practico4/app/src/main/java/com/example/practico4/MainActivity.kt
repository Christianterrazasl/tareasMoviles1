package com.example.practico4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practico4.Screens.CreateRouteScreen
import com.example.practico4.Screens.MapScreen
import com.example.practico4.Screens.RoutesScreen
import com.example.practico4.Screens.SplashScreen
import com.example.practico4.ui.theme.Practico4Theme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practico4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(Modifier.padding(innerPadding)){
                        Navigator()
                    }
                }
            }
        }
    }
}

@Composable
fun Navigator(navController: NavHostController = rememberNavController()){

    val vm: RoutesViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ){
        composable("splash"){
            SplashScreen(navController, vm)
        }
        composable("routes"){
            RoutesScreen(navController, vm)
        }
        composable("map/{routeId}", arguments = listOf(navArgument("routeId"){type = NavType.IntType})){
            backStackEntry ->
            val routeId = backStackEntry.arguments?.getInt("routeId")!!
            MapScreen(routeId, navController, vm)
        }
        composable("createRoute"){
            CreateRouteScreen(navController, vm)
        }


    }

}