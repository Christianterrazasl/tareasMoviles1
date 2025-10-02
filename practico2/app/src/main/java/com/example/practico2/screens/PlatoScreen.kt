package com.example.practico2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.relocation.bringIntoViewResponder
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.practico2.classes.Ingrediente
import com.example.practico2.classes.NavScreens
import com.example.practico2.classes.Receta
import com.example.practico2.viewmodels.MainViewModel

@Composable
fun PlatoScreen(navController: NavHostController, vm: MainViewModel){

    val platoId = vm.selectedPlatoId
    val plato = vm.recetas.find { it.id == platoId }

    Scaffold(Modifier.fillMaxSize()) { innerPadding ->
        Column(verticalArrangement = Arrangement.SpaceBetween,modifier = Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)) {
            Column{
                if (plato != null){
                    Text(text = plato.nombre, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(5.dp))
                    Text(text = "Ingredientes:", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(10.dp))
                    LazyColumn{
                        items(plato.ingredientes){
                            Text(text = it.nombre)
                            Spacer(Modifier.height(5.dp))
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    Text(text = plato.descripcion)
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
    }
}


@Preview
@Composable
fun PreviewPlatoScreen(navController: NavHostController = NavHostController(LocalContext.current), vm: MainViewModel = MainViewModel()){
    PlatoScreen(navController, vm)
}