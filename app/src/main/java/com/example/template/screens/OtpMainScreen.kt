package com.example.template.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.template.data.repository.authRepo.LoginState
import com.example.template.screens.components.otpScreens.EnterCodeUI
import com.example.template.screens.components.otpScreens.ErrorUi
import com.example.template.viewModel.OtpViewModel
import timber.log.Timber

@Composable
fun PhoneLoginUI(
    popUpScreen: () -> Unit,
    viewModel: OtpViewModel = hiltViewModel(),
    restartLogin: () -> Unit
) {
    val context = LocalContext.current

    // Sign up state
    val uiState by viewModel.signUpState.collectAsState(initial = LoginState.NotInitialized)

    // SMS code
    val code by viewModel.code.collectAsState(initial = "")

    // Phone number
    val phone by viewModel.number.collectAsState(initial = "+916392855172")

    val focusManager = LocalFocusManager.current

    when (uiState) {
        // Nothing happening yet
        is LoginState.NotInitialized -> {
            LaunchedEffect(key1 = Unit ){
                viewModel.authenticatePhone()
            }

        }

        // State loading
        is LoginState.Loading -> {
            val text = (uiState as LoginState.Loading).message
            if (text =="Code Sent" ) {
                EnterCodeUI(
                    code = code,
                    onCodeChange = viewModel::onCodeChange,
                    phone = phone,
                    onGo = {
                        Timber.tag("Code Sent").d("The code is " + code)
                        focusManager.clearFocus()
                        viewModel.verifyOtp(code)
                    })
            } else {

                // If the loading state is different form the code sent state,
                // show a progress indicator
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    text?.let {
                        Text(
                            it, modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }

                }


            }

        }

        // If it is the error state, show the error UI
        is LoginState.Error -> {
            val throwable = (uiState as LoginState.Error).message
            if (throwable != null) {
                ErrorUi(exc = throwable, onRestart = restartLogin)
            }
        }

        // You can navigate when the auth process is successful
        is LoginState.Success -> {
            LaunchedEffect(key1 = Unit ){
                Timber.tag("Code").d("The Sign in was successful")
                popUpScreen()
            }

        }

        else -> {}
    }


}