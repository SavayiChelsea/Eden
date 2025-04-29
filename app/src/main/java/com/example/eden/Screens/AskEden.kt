package com.example.eden.Screens

import androidx.compose.material3.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.eden.AuthViewModel
import com.example.eden.R

@Composable
fun AskEden(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val darkGreen = Color(0xFF004D25)
    val lightGreen = Color(0xFF9DBF9D)
    val beige = Color(0xFFF0DCA0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkGreen)
    ) {
        // Background Tree Image
//        Image(
//            painter = painterResource(id = R.drawable.tree),
//            contentDescription = null,
//            modifier = Modifier
//                .matchParentSize()
//                .align(Alignment.Center)
//        )

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

        // Main Content
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicText(
                text = "Hi, I’m Eden.",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold, color = lightGreen)
            )

            Spacer(modifier = Modifier.height(10.dp))

            BasicText(
                text = "Ask anything, from doubt to devotion.",
                style = MaterialTheme.typography.bodyLarge.copy(color = beige)
            )
            Spacer(modifier = Modifier.height(32.dp))
            // Your "Ask Eden" button or content goes here
            // Example placeholder button
//            Box(
//                modifier = Modifier
//                    .background(lightGreen)
//                    .padding(16.dp)
//                    .clickable { /* Handle action */ }
//            ) {
//                Text("Ask Eden", color = darkGreen)
//            }
        }
    }
}