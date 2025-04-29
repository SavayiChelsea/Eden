package com.example.eden

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eden.Screens.*

@Composable
fun AppNavigation(navController: NavHostController, authViewModel: AuthViewModel) {

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("login") {
            LogIn(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable ("signup"){
            SignUp(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable ("home"){
            HomePage(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable ("AskEden"){
            AskEden(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        }

}


