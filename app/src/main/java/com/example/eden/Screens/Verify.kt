package com.example.eden.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.eden.AuthViewModel
import com.example.eden.R

@Composable
fun Verify(navController: NavHostController, authViewModel: AuthViewModel) {
    val darkGreen = Color(0xFF004D25)
    val lightGreen = Color(0xFF9DBF9D)
    val beige = Color(0xFFF0DCA0)
    val LightBeige = Color(0xFFF9E7CC)
    val darkBeige = Color(0xFFC9B581)


    var code by remember { mutableStateOf("".padEnd(4, ' ')) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkGreen)
    ) {
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

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxSize()
                .wrapContentHeight()
                .padding(top = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                //Padlock Image
                Image(
                    painter = painterResource(id = R.drawable.padlock),
                    contentDescription = "Padlock",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(300.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "VERIFY",
                    fontSize = 28.sp,
                    color = beige,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Verification sent to: phone/email.",
                    fontSize = 18.sp,
                    color = LightBeige,
                    fontFamily = FontFamily.SansSerif
                )
                Spacer(modifier = Modifier.height(24.dp))


                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(4) { index ->
                        TextField(
                            value = code.getOrNull(index)?.toString() ?: "",
                            onValueChange = { newValue ->
                                if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                                    val updatedCode = code.toCharArray()
                                    updatedCode[index] = newValue.getOrElse(0) { ' ' }
                                    code = String(updatedCode)
                                }
                            },
                            modifier = Modifier.size(64.dp),
                            textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center),
                            singleLine = true
                        )
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))

                Button(
                    onClick = {
                        navController.navigate("ChangePassword")
                    },

                    //Button colors
                    colors = ButtonDefaults.buttonColors(
                        containerColor = beige,
                        contentColor = darkGreen
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .width(250.dp)
                        .height(50.dp)
                ) {
                    Text(text = "Confirm Code", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(120.dp))


            }

        }
    }
}