package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.em


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var pokemons = Store.getAllPokemon()
    var photoIndex by remember {mutableIntStateOf(0)}
    var pokemonIndex by remember {mutableIntStateOf(Store.pokemonIndex)}
    val context = LocalContext.current

    Column{
        ImageCarousel(pokemonIndex ,photoIndex, onPhotoIndexChange = {photoIndex = it})
        Text(text = pokemons[pokemonIndex].name, fontSize = 8.em, modifier = Modifier.padding(16.dp))
        Text(text = pokemons[pokemonIndex].description, fontSize = 4.em, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize().padding(16.dp)){
            IconButton(
                onClick = {
                    if (Store.pokemonIndex < pokemons.size - 1){
                        photoIndex = 0
                        Store.incrementPokemonIndex()
                        pokemonIndex = Store.pokemonIndex
                    }
                },
                modifier = Modifier
                    .padding(start = 30.dp)
                    .size(90.dp)
                    .background(Color.Black.copy(alpha = 0.3f), CircleShape)
            )
            {
                Icon(
                    painter =  painterResource(R.drawable.baseline_clear_24),
                    contentDescription = "dislike",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }
            IconButton(
                onClick = {
                    Store.likePokemon(pokemons[Store.pokemonIndex])


                    if (Store.pokemonIndex < pokemons.size - 1) {
                        photoIndex = 0
                        Store.incrementPokemonIndex()
                    }


                    val intent = Intent(context, SecondActivity::class.java)

                    context.startActivity(intent)

                },
                modifier = Modifier
                    .padding(start = 30.dp)
                    .size(90.dp)
                    .background(Color.Black.copy(alpha = 0.3f), CircleShape)
            )
            {
                Icon(
                    painter =  painterResource(R.drawable.baseline_check_24),
                    contentDescription = "like",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)

                )
            }
        }
    }

}



@Composable
fun ImageCarousel(pokemonIndex:Int, photoIndex:Int, onPhotoIndexChange:(Int)->Unit){

    val pokemons = Store.getAllPokemon()
    val currentPokemon = pokemons[pokemonIndex]
    Box(Modifier.fillMaxWidth().fillMaxHeight(0.7f).background(color = Color.White)){
        Image(
            painter = painterResource(id = currentPokemon.imagesUrl[photoIndex]),
            contentDescription = "Imagen del pokemon",
            modifier = Modifier.fillMaxSize()
        )
        IconButton(
            onClick = {
                if(photoIndex > 0){
                 onPhotoIndexChange(photoIndex-1)
                }
            },
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 8.dp)
                .background(Color.Black.copy(alpha = 0.3f), CircleShape)
        )
        {
            Icon(
                painter =  painterResource(R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Anterior",
                tint = Color.White
            )
        }
        IconButton(
            onClick = {
                if(photoIndex < currentPokemon.imagesUrl.size - 1){
                    onPhotoIndexChange(photoIndex + 1)
                }
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
                .background(Color.Black.copy(alpha = 0.3f), CircleShape)
        )
        {
            Icon(
                painter =  painterResource(R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = "Siguiente",
                tint = Color.White
            )
        }

    }
}
