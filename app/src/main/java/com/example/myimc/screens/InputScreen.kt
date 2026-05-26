package com.example.myimc.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun InputScreen(navController: NavController) {

    // Estados
    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }

    // Estado para mostrar error
    var mostrarError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Calculadora IMC",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
            },
            label = {
                Text("Nombre")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Peso
        OutlinedTextField(
            value = peso,
            onValueChange = {
                peso = it
            },
            label = {
                Text("Peso (kg)")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Altura
        OutlinedTextField(
            value = altura,
            onValueChange = {
                altura = it
            },
            label = {
                Text("Altura (m)")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Mensaje de error
        if (mostrarError) {
            Text(
                text = "Por favor, ingresa valores válidos",
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = {

                val pesoDouble = peso.toDoubleOrNull()
                val alturaDouble = altura.toDoubleOrNull()

                // Validación
                if (
                    pesoDouble != null &&
                    alturaDouble != null &&
                    pesoDouble > 0 &&
                    alturaDouble > 0
                ) {

                    mostrarError = false

                    // Calcular IMC
                    val imc = pesoDouble / (alturaDouble * alturaDouble)

                    // Navegación
                    navController.navigate(
                        "resultado/$nombre/$imc"
                    )

                } else {

                    mostrarError = true
                }
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Calcular")
        }
    }
}