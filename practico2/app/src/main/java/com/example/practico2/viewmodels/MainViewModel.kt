package com.example.practico2.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.practico2.classes.Ingrediente
import com.example.practico2.classes.Receta

class MainViewModel: ViewModel() {

    val ingredientes = arrayListOf<Ingrediente>(
        Ingrediente(1, "Arroz"),
        Ingrediente(2, "Huevo"),
        Ingrediente(3, "Leche"),
        Ingrediente(4, "Sal"),
        Ingrediente(5, "Azúcar"),
        Ingrediente(6, "Aceite"),
        Ingrediente(7, "Mantequilla"),
        Ingrediente(8, "Queso"),
        Ingrediente(9, "Papa") ,
        Ingrediente(10, "Cebolla"),
        Ingrediente(11, "Zanahoria"),
        Ingrediente(12, "Tomate"),
        Ingrediente(13, "Lechuga"),
        Ingrediente(14, "Pimiento"),
        Ingrediente(15, "Ajo"),
        Ingrediente(16, "Pimienta"),
        Ingrediente(17, "Orégano"),
        Ingrediente(18, "Carne"),
        Ingrediente(19, "Pollo"),
        Ingrediente(20, "Pescado"),
        Ingrediente(21, "Platano")
    )

    val recetas = arrayListOf<Receta>(
        Receta(1,
            "Majadito",
            arrayListOf<Ingrediente>(ingredientes[0], ingredientes[1], ingredientes[17]),
            "Se cocina la carne hasta que quede suave y luego se la desmenuza. Se sofríe con condimentos hasta que tome buen sabor. Paralelamente se prepara el arroz graneado. Finalmente, se mezcla todo y se sirve acompañado de huevo frito y plátano."
        ),
        Receta(
            2,
            "Pollo al horno",
            arrayListOf<Ingrediente>(ingredientes[18], ingredientes[3], ingredientes[0]),
            "Se marina el pollo con condimentos y se deja reposar para que absorba el sabor. Luego se coloca en una fuente, se hornea hasta que quede bien cocido y dorado, rociándolo ocasionalmente con sus propios jugos para mantenerlo jugoso."
        )
    )

    val selectedIngredientes = arrayListOf<Ingrediente>()

    var selectedPlatoId:Int? = null


    fun getRecetasConIngredientes(ingredientesSelected: List<Ingrediente>): ArrayList<Receta>{
        Log.d("Ingredientes seleccionados", ingredientesSelected.toString())
        val recetasConIngredientes = arrayListOf<Receta>()
        for (receta in recetas){
            if (ingredientesSelected.containsAll(receta.ingredientes)){
                recetasConIngredientes.add(receta)
                Log.d("Recetas al momento", recetas.toString())
                Log.d("Receta encontrada", receta.toString())
            }
        }
        return recetasConIngredientes
    }

    fun clearIngredientes(){
        selectedIngredientes.clear()
    }

    val selectedIngredientesCrear = arrayListOf<Ingrediente>()

    fun clearIngredientesCrear() {selectedIngredientesCrear.clear()}

    fun agregarReceta(receta:Receta){
        recetas.add(receta)
    }
}