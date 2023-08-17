package com.paulo.motionanimation.ui.credentials

import androidx.credentials.CredentialManager
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.credentials.PasswordCredential
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun CredentialExampleScreen(viewModel: LoginViewModel = viewModel()) {
    val activity = LocalContext.current.getActivity()
    val credential by viewModel.signedInPasswordCredential.collectAsStateWithLifecycle()

    credential?.let {
        SignedInScreen(
            credential = it,
            logOut = viewModel::simulateLogOut
        )
    } ?: run {
        SignedOutScreen(
            signInWithEnteredCredentials = { username, password ->
                //Perform your app's sign in or sign up logic here.
                //viewModel.signInOrSignUp( ... )

                //Then if successful, save the credentials
                activity?.let {
                    viewModel.signInOrSignUpWithEnteredCredential(activity, username, password)
                }
            },

            signInWithSavedCredentials = {
                activity ?: return@SignedOutScreen
                viewModel.signInWithSavedCredential(activity)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignedOutScreen(
    signInWithEnteredCredentials: (username: String, password: String) -> Unit,
    signInWithSavedCredentials: () -> Unit
) {
    var username by remember { mutableStateOf("yourname@email.com") }
    var password by remember { mutableStateOf("abc123") }

    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Username")
        TextField(
            value = username,
            onValueChange = { username = it }
        )

        Text(modifier = Modifier.padding(top = 20.dp), text = "Password")
        TextField(
            visualTransformation = PasswordVisualTransformation(),
            value = password,
            onValueChange = { password = it }
        )

        Button(onClick = {
            signInWithEnteredCredentials(username, password)
        }) {
            Text("Register / Sign In")
        }

        Text(modifier = Modifier.padding(vertical = 50.dp), text = "-- OR --")
        Button(onClick = {
            signInWithSavedCredentials()
        }) {
            Text("Sign In With Saved Credentials")
        }

    }
}

@Composable
fun SignedInScreen(credential: PasswordCredential, logOut: () -> Unit) {
    Column {
        Text("You have successfully logged in.")
        Text("Username: ${credential.id}")
        Text("Password: ${credential.password}")
        Button (onClick = { logOut() }) {
            Text("Sign Out")
        }
    }
}

//Utility function to get the Activity from a composable, using LocalContext
fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}