package com.example.practico2.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.practico2.classes.Ingrediente
import com.example.practico2.classes.NavScreens
import com.example.practico2.classes.Receta
import com.example.practico2.viewmodels.MainViewModel

@Composable
fun AgregarPlatoScreen(navController: NavHostController, vm: MainViewModel){

    var descripcion by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }

    Scaffold (Modifier.fillMaxSize()){ innerPadding ->
        Column(Modifier.padding(innerPadding).padding(0.dp,10.dp)) {
            Row(Modifier.padding(10.dp)) {
                Text(text = "Agregar plato", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }
            IngredientesGridCrear(vm.ingredientes, vm)

            Row(Modifier.padding(5.dp)) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = {nombre = it},
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Row(Modifier.padding(5.dp)) {
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = {descripcion = it},
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 5
                )
            }

            Row(horizontalArrangement = Arrangement.Center ,modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                Button(
                    onClick = {
                        val recetas = vm.recetas
                        val lastId = recetas[recetas.size-1].id
                        val receta = Receta(lastId+1, nombre, ArrayList(vm.selectedIngredientesCrear), descripcion)
                        Log.d("Receta agregada", receta.toString())
                        vm.agregarReceta(receta)
                        vm.clearIngredientesCrear()
                        navController.navigate(NavScreens.INGREDIENTES.name)
                    }
                ) { Text(text = "Agregar receta")}
                Spacer(Modifier.width(10.dp))
                Button(
                    onClick = {
                        vm.clearIngredientesCrear()
                        navController.navigate(NavScreens.INGREDIENTES.name)
                    }
                ) { Text(text = "Cancelar")}
            }
        }
    }
}

@Composable
fun IngredientesGridCrear(ingredientes: List<Ingrediente>, vm: MainViewModel){
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), modifier = Modifier.padding(5.dp, 5.dp)
    ){
        items(ingredientes){ item ->
            IngredienteItemCrear(item, vm)
        }
    }
}


@Composable
fun IngredienteItemCrear(ingrediente: Ingrediente, vm: MainViewModel){

    var isSelected by remember { mutableStateOf(false) }

    Button(
        onClick = {
            if (isSelected) {
                vm.selectedIngredientesCrear.remove(ingrediente)
                isSelected = false
            } else {
                vm.selectedIngredientesCrear.add(ingrediente)
                Log.d("Ingrediente agregado", ingrediente.nombre)
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

@Preview
@Composable
fun AgregarPlatoScreenPreview(){
    AgregarPlatoScreen(NavHostController(LocalContext.current), viewModel())
}

