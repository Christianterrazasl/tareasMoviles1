package com.example.practicofinal

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicofinal.Screens.AgregarMascotaScreen
import com.example.practicofinal.Screens.HomeScreen
import com.example.practicofinal.Screens.LoginScreen
import com.example.practicofinal.Screens.MascotasScreen
import com.example.practicofinal.Screens.PaseosScreen
import com.example.practicofinal.Screens.RegisterScreen
import com.example.practicofinal.Screens.SplashScreen
import com.example.practicofinal.ui.theme.PracticoFinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticoFinalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        Navigator()
                    }
                }
            }
        }
    }
}

@Composable
fun Navigator(navController : NavHostController = rememberNavController()) {

    val vm : PaseoViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "splash"
    ){
        composable("splash"){
            SplashScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController, vm = vm)
        }
        composable("login"){
            LoginScreen(navController = navController, vm = vm)
        }
        composable("register"){
            RegisterScreen(navController = navController, vm = vm)
        }
        composable("mascotas") {
            MascotasScreen(navController = navController, vm = vm)
        }
        composable("paseos") {
            PaseosScreen(navController = navController, vm = vm)
        }
        composable("agregarMascota") {
            AgregarMascotaScreen(navController, vm)
        }

    }

}
