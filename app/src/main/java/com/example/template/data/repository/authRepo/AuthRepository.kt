package com.example.template.data.repository.authRepo


import kotlinx.coroutines.flow.MutableStateFlow


interface AuthRepository {

    val signUpState: MutableStateFlow<LoginState>
    suspend fun authenticate()
    suspend fun verifyOtp(code: String)
}
sealed class LoginState {
    object NotInitialized : LoginState()
    class Loading(val message: String?) : LoginState()
    class Success(val message: String?) : LoginState()
    class Error(val message: String?) : LoginState()
}