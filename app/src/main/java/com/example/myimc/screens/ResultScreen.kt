package com.example.myimc.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.Locale

@Composable
fun ResultScreen(
    nombre: String,
    imc: Double,
    navController: NavController
) {

    // Clasificación IMC
    val categoria = when {

        imc < 18.5 -> "Bajo peso"

        imc < 25.0 -> "Peso normal"

        imc < 30.0 -> "Sobrepeso"

        else -> "Obesidad"
    }

    // Color dinámico
    val colorCategoria = when {

        imc < 18.5 -> Color.Red

        imc < 25.0 -> Color.Green

        imc < 30.0 -> Color(0xFFFF9800)

        else -> Color.Red
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Hola $nombre, tu resultado es:",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = String.format(
                Locale.US,
                "%.1f",
                imc
            ),
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = categoria,
            color = colorCategoria,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {

                navController.popBackStack()
            }
        ) {

            Text("Volver")
        }
    }
}