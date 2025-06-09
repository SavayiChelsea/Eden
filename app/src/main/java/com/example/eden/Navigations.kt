package com.example.eden

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eden.Screens.AskEden
import com.example.eden.Screens.ChangePassword
import com.example.eden.Screens.ForgotPassword
import com.example.eden.Screens.HomePage
import com.example.eden.Screens.LogIn
import com.example.eden.Screens.Profile
import com.example.eden.Screens.SignUp
import com.example.eden.Screens.SplashScreen
import com.example.eden.Screens.TermsAndConditions
import com.example.eden.Screens.Verify

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
        composable("TermsAndConditions"){
            TermsAndConditions(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable("ForgotPassword"){
            ForgotPassword(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable("Verify"){
            Verify(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable("ChangePassword"){
            ChangePassword(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable("Profile"){
            Profile(
                navController = navController,
                authViewModel = authViewModel
            )
        }

    }

}


