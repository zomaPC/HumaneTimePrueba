package com.example.humanetime.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.humanetime.ui.screen.LoginScreen
import com.example.humanetime.ui.screen.AdminScreen

@Composable
fun Navigator(startDestination: String = Routes.LOGIN) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(Routes.ADMIN) {
            AdminScreen()
        }
    }
}