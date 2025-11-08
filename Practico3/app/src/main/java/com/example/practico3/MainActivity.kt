package com.example.practico3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.practico3.screens.CreatePlaceScreen
import com.example.practico3.screens.CreateTripScreen
import com.example.practico3.screens.HomeScreen
import com.example.practico3.screens.MyTripsScreen
import com.example.practico3.screens.PlaceDetailScreen
import com.example.practico3.screens.SplashScreen
import com.example.practico3.screens.TripDetailScreen
import com.example.practico3.ui.theme.Practico3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practico3Theme {
                Navigator()
            }
        }
    }
}

@Composable
fun Navigator(navController: NavHostController = rememberNavController()){

    val vm: TripViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ){
        composable("home"){
            HomeScreen(navController,vm)
        }
        composable ("splash"){
            SplashScreen(navController,vm)
        }
        composable(route = "tripDetail/{tripId}",
            arguments = listOf(navArgument("tripId"){type = NavType.IntType})
            ){
            backStackEntry ->
            val tripId = backStackEntry.arguments?.getInt("tripId")
            TripDetailScreen(tripId = tripId, vm = vm, navController = navController)
        }
        composable("myTrips"){
            MyTripsScreen(navController, vm)
        }

        composable(route = "placeDetail/{placeId}",
            arguments = listOf(navArgument("placeId"){type = NavType.IntType})){
            backStackEntry ->
            val placeId = backStackEntry.arguments?.getInt("placeId")
            PlaceDetailScreen(placeId, navController,vm)
        }

        composable ("createTrip") {
            CreateTripScreen(navController, vm)
        }

        composable ("createPlace/{tripId}",
            arguments = listOf(navArgument("tripId"){type = NavType.IntType})
            ) {
            backStackEntry ->
            val tripId = backStackEntry.arguments?.getInt("tripId")
            CreatePlaceScreen(tripId ,navController, vm)
        }

    }
}



