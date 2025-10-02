package com.example.practico2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.practico2.classes.NavScreens
import com.example.practico2.classes.Receta
import com.example.practico2.viewmodels.MainViewModel

@Composable
fun ResultadosScreen(navController: NavHostController, vm: MainViewModel) {

    val recetas = vm.getRecetasConIngredientes(vm.selectedIngredientes)

    Scaffold(Modifier.fillMaxSize()) { innerPadding ->

        Column(Modifier.fillMaxSize().padding(innerPadding).padding(10.dp), verticalArrangement = Arrangement.SpaceBetween){
            if (recetas.isNotEmpty()){
                Column {
                    Text(text = "Recetas", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    LazyColumn(Modifier.padding(10.dp), verticalArrangement = Arrangement.SpaceBetween){

                        items(recetas){
                            RecetaItem(it, navController, vm)
                        }
                    }
                }
                Button(
                    onClick = {
                        vm.clearIngredientes()
                        vm.selectedPlatoId = 0
                        navController.navigate(NavScreens.INGREDIENTES.name)
                    }
                ) {
                    Text(text = "Volver a inicio")
                }
            }
            else{
                Column {
                    Text(text = "No se encontraron recetas con los ingredientes seleccionados")
                    Button(
                        onClick = {
                            navController.navigate(NavScreens.AGREGAR_PLATO.name)
                        }
                    ) {
                        Text(text = "Agregar receta")
                    }

                    Button(
                        onClick = {
                            navController.navigate(NavScreens.INGREDIENTES.name)
                        }
                    ) {
                        Text(text = "Cancelar")
                    }
                }
            }
        }

    }
}

@Composable
fun RecetaItem(receta: Receta, navController: NavHostController, vm: MainViewModel){
    Button(onClick = {
        vm.selectedPlatoId = receta.id
        navController.navigate(NavScreens.PLATO.name)
    }, shape = RoundedCornerShape(6.dp),modifier=Modifier.fillMaxWidth()
    ) {
        Text(text = receta.nombre)
    }
}