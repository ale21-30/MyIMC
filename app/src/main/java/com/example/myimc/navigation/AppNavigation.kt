package com.example.myimc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myimc.screens.InputScreen
import com.example.myimc.screens.ResultScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "input"
    ) {

        // Pantalla 1
        composable("input") {

            InputScreen(navController)
        }

        // Pantalla 2
        composable(

            route = "resultado/{nombre}/{imc}",

            arguments = listOf(

                navArgument("nombre") {
                    type = NavType.StringType
                },

                navArgument("imc") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val nombre =
                backStackEntry.arguments
                    ?.getString("nombre")
                    ?: ""

            val imc =
                backStackEntry.arguments
                    ?.getString("imc")
                    ?.toDoubleOrNull()
                    ?: 0.0

            ResultScreen(
                nombre = nombre,
                imc = imc,
                navController = navController
            )
        }
    }
}