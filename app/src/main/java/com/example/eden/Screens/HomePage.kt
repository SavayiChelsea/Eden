package com.example.eden.Screens

import com.example.eden.Screens.*
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eden.AuthState
import com.example.eden.AuthViewModel
import com.example.eden.R
import com.example.eden.ui.theme.CustomBeige
import com.example.eden.ui.theme.CustomSoftRed
import com.example.eden.ui.theme.EdenGreen
import com.example.eden.ui.theme.LightCream
import com.example.eden.ui.theme.LightPeach

@Composable
fun HomePage (navController: NavHostController, authViewModel: AuthViewModel) {
    val darkGreen = Color(0xFF004D25)
    val lightGreen = Color(0xFF9DBF9D)
    val beige = Color(0xFFF0DCA0)

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(key1 = authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")

            else -> Unit
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF004D25))
            .padding(16.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier.size(34.dp),
                    tint = CustomBeige
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🔥3", color = Color.White, fontSize = 20.sp)
                Spacer(modifier = Modifier.width(12.dp))
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.account), // replace with your profile icon
                        contentDescription = "Profile",
                        tint = CustomSoftRed
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Top Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp) // Add padding at the top
        ) {
            listOf("Ask Eden", "Games", "Prayer Wall").forEach { label ->
                AssistChip(
                    onClick = {
                        when (label) {
                            "Ask Eden" -> {
                                navController.navigate("AskEden")                            }
                            "Games" -> {
                                // Action for Games
                            }
                            "Prayer Wall" -> {
                                // Action for Prayer Wall
                            }
                        }
                    },
                    label = {
                        Text(label, color = LightPeach, fontSize = 15.sp)
                    },

                    border = BorderStroke(2.dp, LightPeach),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Verse Card
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()

        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF255027),
                                Color(0xFF737373)
                            )
                        )
                    )
                    .padding(16.dp) // apply padding here
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Verse of the Day", fontSize = 18.sp, color = LightCream)
                    Text("John 16:20 NIV", fontSize = 16.sp, color = CustomBeige)

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Very truly I tell you, you will weep and mourn while the world rejoices. you will grieve, but your grief will turn to joy.",
                        fontSize = 16.sp,
                        color = CustomBeige
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = LightPeach),
                            shape = RoundedCornerShape(54)
                        ) {
                            Text("Go deeper", color = Color.Black, fontSize = 16.sp)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    painter = painterResource(id = R.drawable.heartt),
                                    contentDescription = null,
                                    tint = Color.Red,
                                    modifier = Modifier.size(30.dp)
                                )
                                Text("200", color = Color.White, fontSize = 14.sp)
                            }
                            Spacer(modifier = Modifier.width(12.dp))

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow),
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                                Text("200", color = Color.White, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Devotional Generator

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9E7CC))
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                // Left Column for Icon (Emoji)
                Column(
                    modifier = Modifier.padding(end = 12.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.sunrise), // Replace with your emoji icon
                        contentDescription = "sunrise",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }

                // Right Column for Text
                Column {
                    Text(
                        "Devotional Generator",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                    Text(
                        "Generate a devotional that is tailored to you.",
                        fontSize = 14.sp,
                        color = Color.Black)
                }
            }
        }



        Spacer(modifier = Modifier.height(24.dp))

        // Four Buttons - Centered Version
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Calculate width for buttons accounting for spacing
            val buttonWidth = (LocalConfiguration.current.screenWidthDp.dp - 16.dp * 2 /* padding */ - 12.dp /* spacing */) / 2

            // First row
            Row(
                modifier = Modifier.width(buttonWidth * 2 + 12.dp), // Total width of both buttons + spacing
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                HomeButton("Bible", modifier = Modifier.width(buttonWidth)){/*put navigation here*/}
                HomeButton("My Journal", modifier = Modifier.width(buttonWidth)){/*put navigation here*/}
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Second row
            Row(
                modifier = Modifier.width(buttonWidth * 2 + 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                HomeButton("Plans", modifier = Modifier.width(buttonWidth)){/*put navigation here*/}
                HomeButton("Trivia Time", modifier = Modifier.width(buttonWidth)){/*put navigation here*/}
            }

            Spacer(modifier = Modifier.height(12.dp))

            //third row
            Row(
                modifier = Modifier.width(buttonWidth * 2 + 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
                HomeButton("Sign out", modifier = Modifier.width(buttonWidth)){
                    authViewModel.signout()
                }
                }

        }


    }
}

@Composable
fun HomeButton(label: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF9E7CC)),
        modifier = modifier
            .height(50.dp) // Fixed height
    ) {
        Text(
            label,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 2.dp) // Inner padding for text
        )
    }
}







@Preview(showBackground = true)
@Composable
fun HomePagePreview() {

    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    HomePage(navController = navController, authViewModel = authViewModel)
}
