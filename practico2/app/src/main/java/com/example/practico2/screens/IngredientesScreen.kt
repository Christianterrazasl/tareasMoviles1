package com.example.practico2.screens

import android.R
import android.util.Half.round
import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practico2.classes.Ingrediente
import com.example.practico2.classes.NavScreens
import com.example.practico2.viewmodels.MainViewModel
import kotlin.math.round

@Composable
fun IngredientesScreen(navController: NavHostController, vm: MainViewModel){
    val ingredientes = vm.ingredientes

    Scaffold(modifier = Modifier.fillMaxSize().background(Color.White)) { innerPadding ->
        Column(
            Modifier.fillMaxSize().padding(innerPadding)){

            Row(Modifier.padding(20.dp)){
                Text(text="Ingredientes", fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
            }

            IngredientesGrid(ingredientes, vm)

            Row (Modifier.padding(10.dp)){
                Button(onClick = {
                    navController.navigate(NavScreens.RESULTADOS.name)}
                ) {
                    Text(text = "Buscar recetas")
                }
            }
        }
    }
}

@Composable
fun IngredienteItem(ingrediente: Ingrediente, vm: MainViewModel){

    var isSelected by remember { mutableStateOf(false) }

    Button(
        onClick = {
            if (isSelected) {
                vm.selectedIngredientes.remove(ingrediente)
                isSelected = false
            } else {
                vm.selectedIngredientes.add(ingrediente)
                isSelected = true
            }
        },
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.padding(3.dp, 3.dp), colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(57,93,177) else Color.Gray,
        )
    ) {
        Text(text = ingrediente.nombre)
    }
}

@Composable
fun IngredientesGrid(ingredientes: List<Ingrediente>, vm: MainViewModel){
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), modifier = Modifier.padding(5.dp, 10.dp)
    ){
        items(ingredientes){ item ->
            IngredienteItem(item, vm)
        }
    }
}

@Preview
@Composable
fun IngredientesScreenPreview(){
    IngredientesScreen(NavHostController(LocalContext.current), viewModel())
}