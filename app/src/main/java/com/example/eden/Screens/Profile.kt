package com.example.eden.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.eden.AuthViewModel

@Composable
fun Profile(navController: NavHostController,
            authViewModel: AuthViewModel
) {val darkGreen = Color(0xFF004D25)
    val lightGreen = Color(0xFF9DBF9D)
    val beige = Color(0xFFF0DCA0)

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    // Access webClientId from authViewModel
    val webClientId = authViewModel.webClientId

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkGreen)
    ){
        // Back Button
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = "Back",
                tint = lightGreen
            )
        }
        Column (
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            BasicText(
                text = "Profile Page",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = lightGreen
                )
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            HomeButton(
                label = "Sign Out",
                modifier = Modifier
                    .width(150.dp), // Give it a proper width
                onClick = {
                    authViewModel.signOut(context, webClientId) {
                        Toast.makeText(context, "Logout Successful", Toast.LENGTH_SHORT).show()
                    }
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }




    }

}