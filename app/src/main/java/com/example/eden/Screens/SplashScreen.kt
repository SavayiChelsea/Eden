package com.example.eden.Screens

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eden.R
import kotlinx.coroutines.delay
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.eden.AuthViewModel

@Composable
fun SplashScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    LaunchedEffect(true) {
        delay(2000) // 2 seconds splash time
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    SplashScreenContent()
}

@Preview(showBackground = true, backgroundColor = 0xFF004D25)
@Composable
fun SplashScreenPreview() {
    SplashScreenContent()
}

@Composable
fun SplashScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF004D25)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.tree),
                contentDescription = "Tree Icon",
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "EDEN",
                fontSize = 36.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "In the Garden of God’s Word, We Thrive.",
                fontSize = 14.sp,
                color = Color.White,
                fontFamily = FontFamily.Cursive
            )
        }
    }
}