package com.example.myapplication

import android.R.attr.contentDescription
import android.content.Context
import android.content.Intent
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen2(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen2(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(Modifier.padding(top=64.dp)){
        IconButton(
            onClick = {
                val intent = Intent(context, MainActivity::class.java)

                context.startActivity(intent)
            },
            modifier = Modifier.background(Color.Black).padding(16.dp)
        ) {
            Icon(
                painter =  painterResource(R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Anterior",
                tint = Color.White
            )
        }

        LazyColumn {

            items(Store.getLikedPokemon()){ it->
                Column{
                PokemonItem(it)
                Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color.LightGray))
                }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon:Pokemon){
    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically ,modifier = Modifier.fillMaxWidth().padding(16.dp)){
        Text(text = pokemon.name)
        Text(text = pokemon.type)
        Image(
            painter = painterResource(id = pokemon.imagesUrl[0]),
            contentDescription = "Imagen del pokemon",
            modifier = Modifier.height(100.dp)
        )
    }
}
