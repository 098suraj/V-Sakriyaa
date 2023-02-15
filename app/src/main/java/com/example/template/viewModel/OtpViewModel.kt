package com.example.template.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.data.repository.authRepo.AuthRepository
import com.example.template.data.repository.authRepo.LoginState
import com.example.template.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val accountService: AuthRepository,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {
    val signUpState: MutableStateFlow<LoginState> = accountService.signUpState
    private val _number: MutableStateFlow<String> = MutableStateFlow("")

    val number: StateFlow<String> get() = _number

    private val _code: MutableStateFlow<String> = MutableStateFlow("")
    val code: StateFlow<String> get() = _code

    fun authenticatePhone() {
        viewModelScope.launch (dispatcherProvider.io){
           accountService.authenticate()
        }

    }

    fun onCodeChange(code: String) {
        _code.value = code.take(6)
    }

    fun verifyOtp(code: String) {
        viewModelScope.launch {
            accountService.verifyOtp(code)
        }
    }
}