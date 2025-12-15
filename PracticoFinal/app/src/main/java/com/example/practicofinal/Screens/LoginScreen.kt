package com.example.practicofinal.Screens

import android.graphics.Outline
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.practicofinal.PaseoViewModel

@Composable
fun LoginScreen(navController: NavController, vm: PaseoViewModel){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }


    LaunchedEffect(vm.loginSuccess) {
        if (vm.loginSuccess) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize().padding(14.dp)) {
        Text(text = "Login", fontSize = 24.sp, fontWeight = FontWeight.SemiBold )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "Email") }
        )
        if (emailError){
            Text(text = "Email invalido", color = Color.Red)
        }

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Contraseña") }
        )
        if (passwordError){
            Text(text = "Contraseña invalida", color = Color.Red)
        }
        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                if (email.isEmpty()) {
                    emailError = true
                }
                if (password.isEmpty()) {
                    passwordError = true
                }
                if (emailError || passwordError) {
                    return@Button
                }
                vm.login(email, password)
            }
        ) {
            Text("Ingresar")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate("register")
            }
        ) {
            Text("Registrarme")
        }

    }
}