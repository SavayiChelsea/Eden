package com.example.eden.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eden.AuthViewModel
import com.example.eden.R

@Composable
fun ForgotPassword(navController: NavHostController, authViewModel: AuthViewModel) {
    val darkGreen = Color(0xFF004D25)
    val lightGreen = Color(0xFF9DBF9D)
    val beige = Color(0xFFF0DCA0)
    val LightBeige = Color(0xFFF9E7CC)
    val darkBeige = Color(0xFFC9B581)

    var email by remember { mutableStateOf("") }

    Box (
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
        ){
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
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                //Padlock Image
                Image(
                    painter = painterResource(id = R.drawable.padlock),
                    contentDescription = "Padlock",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(300.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "RESET PASSWORD",
                    fontSize = 28.sp,
                    color = beige,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Please write your email or phone number to " +
                            "receive a confirmation code  to set a new password.",
                    fontSize = 18.sp,
                    color = LightBeige,
                    fontFamily = FontFamily.SansSerif
                )
                Spacer(modifier = Modifier.height(24.dp))

                //Email/ Phone number
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("EMAIL", fontSize = 14.sp, color = lightGreen) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email Icon",
                            tint = lightGreen,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = beige,
                        unfocusedBorderColor = beige,
                        cursorColor = beige,
                        focusedTextColor = beige,
                        unfocusedTextColor = beige
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(64.dp))

                Button(
                    onClick = {
                        navController.navigate("Verify")
                    },

                    //Button colors
                    colors = ButtonDefaults.buttonColors(
                        containerColor = beige,
                        contentColor = darkGreen
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .width(300.dp)
                        .height(40.dp)
                ) {
                    Text(text = "Verify", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    }
    
}

@Composable
@Preview(showBackground = true)
fun ForgotPasswordPreview(){
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    ForgotPassword(navController = navController, authViewModel = authViewModel)
}
