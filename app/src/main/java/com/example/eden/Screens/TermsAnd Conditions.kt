package com.example.eden.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.eden.AuthViewModel

@Composable
fun TermsAndConditions(
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
        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Terms and Conditions",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = beige
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "1. Account Responsibility",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = lightGreen
            )
            Text(
                text = "Users are responsible for maintaining the confidentiality of their account credentials and are fully responsible for all activities that occur under their account.",
                fontSize = 14.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "2. Usage Policy",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = lightGreen
            )
            Text(
                text = "You agree not to misuse the app’s services or help anyone else do so. This includes violating any laws or distributing harmful content.",
                fontSize = 14.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "3. Data Privacy",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = lightGreen
            )
            Text(
                text = "We are committed to protecting your data and ensuring your information is handled responsibly and transparently.",
                fontSize = 14.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "By using this app, you agree to comply with these terms.",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = beige
            )

            Spacer(modifier = Modifier.height(32.dp))
        }

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
    }
}
