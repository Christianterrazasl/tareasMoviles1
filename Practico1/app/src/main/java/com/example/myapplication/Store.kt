package com.example.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

object Store {

    val allPokemons = arrayListOf<Pokemon>(
        Pokemon(1,"Charmander", "Fuego", "Pokemon inicial en su primera fase",
            arrayListOf(R.drawable.charmander1, R.drawable.charmander2, R.drawable.charmander3, R.drawable.charmander4) ),
        Pokemon(2,"Squirtle", "Agua", "Pokemon inicial en su primera fase",
            arrayListOf(R.drawable.squirtle1, R.drawable.squirtle2, R.drawable.squirtle3, R.drawable.squirtle4) ),
        Pokemon(3,"Machop", "Lucha", "Pokemon tipo lucha en su primera fase",
            arrayListOf(R.drawable.machop1, R.drawable.machop2, R.drawable.machop3, R.drawable.machop4)),
        Pokemon(4,"Polywrath", "Agua", "Pokemon tipo agua en su segunda fase",
            arrayListOf(R.drawable.polywrath1, R.drawable.polywrath2, R.drawable.polywrath3, R.drawable.polywrath4)),
        Pokemon(5,"Pidgey", "Volador", "Pokemon tipo volador en su primera fase",
            arrayListOf(R.drawable.pidgey, R.drawable.pidgey2, R.drawable.pidgey3, R.drawable.pidgey4)),
        Pokemon(6,"Chikorita", "Planta", "Pokemon tipo planta en su primera fase",
            arrayListOf(R.drawable.chikorita1, R.drawable.chikorita2, R.drawable.chikorita3, R.drawable.chikorita4)),
        Pokemon(7,"Ratatta", "Normal", "Pokemon de los que aparecen al principio",
            arrayListOf(R.drawable.ratatta1, R.drawable.ratatta2, R.drawable.ratatta3, R.drawable.ratatta4)),
        Pokemon(8,"Zubat", "Oscuro", "Pokemon de cuevas",
            arrayListOf(R.drawable.zubat1, R.drawable.zubat2, R.drawable.zubat3, R.drawable.zubat4)),
        Pokemon(9,"Eevee", "normal", "Pokemon que evoluciona a varios tipos",
            arrayListOf(R.drawable.eevee1, R.drawable.eevee2, R.drawable.eevee3, R.drawable.eevee4))

    )

    val likedPokemons:MutableList<Pokemon> = mutableListOf()
    var pokemonIndex by mutableIntStateOf(0)

    fun getAllPokemon(): ArrayList<Pokemon>{
        return allPokemons
    }
    fun likePokemon(pokemon:Pokemon){
        likedPokemons.add(pokemon)
    }
    fun getLikedPokemon():MutableList<Pokemon>{
        return likedPokemons

    }
    fun incrementPokemonIndex(){
        pokemonIndex++
    }
}