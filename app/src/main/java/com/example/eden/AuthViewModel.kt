package com.example.eden

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore


class AuthViewModel : ViewModel(){
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    val webClientId = "743460035156-qkh8h6siek7597tu0si8vg3am3ctnmue.apps.googleusercontent.com"


    private val _authState = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState

    init{

        checkAuthStatus()
    }

    //Check whether you are logged in or not
    fun checkAuthStatus(){
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    //On clicking the log in button

    fun login(email : String, password : String){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }

        _authState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message ?: "Login Failed")
                }

            }
    }

    fun signup(username: String, email: String, password: String, confirmPassword: String) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            _authState.value = AuthState.Error("All fields must be filled")
            return
        }

        if (password != confirmPassword) {
            _authState.value = AuthState.Error("Passwords do not match")
            return
        }

        _authState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        val userId = it.uid
                        val userDetails = mapOf(
                            "username" to username,
                            "email" to email

                            )

                        Log.d("Signup", "User details: $userId")
                        db.collection("users").document(userId)
                            .set(userDetails)
                            .addOnSuccessListener {
                                _authState.value = AuthState.Authenticated
                                Log.d("Signup", "User details saved successfully")
                            }
                            .addOnFailureListener { e ->
                                _authState.value = AuthState.Error("Failed to save user details: ${e.message}")

                                Log.e("Signup", "Failed to save user details", e)
                            }
                    }                } else {
                    _authState.value = AuthState.Error(task.exception?.message?:"Try Again!")
                }
            }
    }

    fun signInWithGoogle(context: Context, idToken: String, onComplete: () -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                    onComplete() // Notify that sign-in was successful
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Sign-in failed")
                }
            }
    }

    fun signOut(context: Context, webClientId: String, onComplete: () -> Unit) {
        // Firebase sign out
        FirebaseAuth.getInstance().signOut()

        // Google sign out
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
        googleSignInClient.signOut().addOnCompleteListener {
            _authState.value = AuthState.Unauthenticated
            onComplete()
        }
    }

}




sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()

}