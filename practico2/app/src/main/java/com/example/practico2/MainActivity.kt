package com.example.practico2

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practico2.classes.NavScreens
import com.example.practico2.screens.AgregarPlatoScreen
import com.example.practico2.screens.IngredientesScreen
import com.example.practico2.screens.PlatoScreen
import com.example.practico2.screens.ResultadosScreen
import com.example.practico2.ui.theme.Practico2Theme
import com.example.practico2.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practico2Theme {
                NavApp()
            }
        }
    }
}

@Composable
fun NavApp(navController: NavHostController = rememberNavController())  {

    val vm: MainViewModel = viewModel()
    NavHost(navController = navController, startDestination = NavScreens.INGREDIENTES.name){
        composable(NavScreens.INGREDIENTES.name){
            IngredientesScreen(navController, vm)
        }
        composable(NavScreens.RESULTADOS.name){
            ResultadosScreen(navController, vm)
        }
        composable(NavScreens.PLATO.name){
            PlatoScreen(navController, vm)
        }
        composable(NavScreens.AGREGAR_PLATO.name){
            AgregarPlatoScreen(navController, vm)
        }
    }
}
