package com.example.eden.Screens

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eden.AuthState
import com.example.eden.AuthViewModel
import com.example.eden.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
fun SignUp(navController: NavHostController, authViewModel: AuthViewModel) {
    val darkGreen = Color(0xFF004D25)
    val lightGreen = Color(0xFF9DBF9D)
    val beige = Color(0xFFF0DCA0)

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var termsAccepted by remember { mutableStateOf(false) }

    //get authstate
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    //Google Sign In Logic
    val googleSignInOptions = remember{
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("743460035156-qkh8h6siek7597tu0si8vg3am3ctnmue.apps.googleusercontent.com")
            .requestEmail()
            .build()
    }

    val googleSignInClient = remember {
        GoogleSignIn.getClient(context, googleSignInOptions)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.result
            Log.d("GoogleSignIn", "Google Account Retrieved: ${account.email}")
            // Call the signInWithGoogle method in AuthViewModel
            authViewModel.signInWithGoogle(context, account.idToken!!) {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        } catch (e: Exception) {
            Log.e("GoogleSignIn", "Exception during sign-in: ${e.message}")
        }
    }


    // After signing up

    LaunchedEffect(key1 = authState.value) {
        when (val state = authState.value) {
            is AuthState.Authenticated -> {
                Log.d("AuthState", "User successfully signed up")
                navController.navigate("home")
            }
            is AuthState.Error -> {
                Log.e("AuthState", "Sign-up error: ${state.message}")
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkGreen)
            .padding(24.dp)
    ) {
        // Back Button
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)

        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = "Back",
                tint = lightGreen
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()), //Make column scrollable
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                //Tree Logo
                Image(
                    painter = painterResource(id = R.drawable.tree),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(160.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                //Username
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("USERNAME", fontSize = 14.sp, color = lightGreen) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "Username Icon",
                            tint = lightGreen,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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

                Spacer(modifier = Modifier.height(8.dp))

                //Email
                OutlinedTextField(
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

                Spacer(modifier = Modifier.height(8.dp))

                //Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("PASSWORD", fontSize = 14.sp, color = lightGreen) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password Icon",
                            tint = lightGreen,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = Icons.Outlined.Visibility,
                                contentDescription = "Toggle Password",
                                tint = lightGreen,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

                Spacer(modifier = Modifier.height(8.dp))

                //  Confirm Pass
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("CONFIRM PASSWORD", fontSize = 14.sp, color = lightGreen) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Confirm Password Icon",
                            tint = lightGreen,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                imageVector = Icons.Outlined.Visibility,
                                contentDescription = "Toggle Confirm Password",
                                tint = lightGreen,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

                Spacer(modifier = Modifier.height(8.dp))

                // Terms and Conditions
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Checkbox(
                        checked = termsAccepted,
                        onCheckedChange = { termsAccepted = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = beige,
                            uncheckedColor = lightGreen
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "I agree with the Terms & Conditions",
                        fontSize = 12.sp,
                        color = beige,
                        modifier = Modifier.clickable {
                            navController.navigate("TermsAndConditions")
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            //Sign Up Button
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = {
                        authViewModel.signup(
                            username,
                            email,
                            password,
                            confirmPassword
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = beige,
                        contentColor = darkGreen
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    Text(text = "Sign Up", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(24.dp))

                //SignIn with Google
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "or sign in with", fontSize = 16.sp, color = beige)
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google logo",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                // authViewModel.signInWithGoogle(navController)
                                val signInIntent = googleSignInClient.signInIntent
                                launcher.launch(signInIntent)
                            },
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                //Back to Login
                Text(
                    text = "Already have an account? Log In",
                    fontSize = 16.sp,
                    color = beige,
                    modifier = Modifier.clickable {
                        navController.navigate("login")
                    }
                )


            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SignUpPreview() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    SignUp(navController = navController, authViewModel = authViewModel)
}
