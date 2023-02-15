package com.example.template.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.template.data.presistance.SettingsStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsStore: SettingsStore
):ViewModel() {
    private val _state= MutableStateFlow<Boolean>(false)
    val state=_state.asStateFlow()

    fun getState(){
        viewModelScope.launch {
           _state.value= settingsStore.getUserLoggedInStatus()
        }
    }
}