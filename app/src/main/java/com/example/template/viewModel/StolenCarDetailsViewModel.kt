package com.example.template.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.data.repository.AuthService
import com.example.template.model.CarDetailsResponse
import com.example.template.model.stolenCarDetails
import com.example.template.utils.DispatcherProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class StolenCarDetailsViewModel @Inject constructor(
    private val authService: AuthService,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _stolenCarDetailsResponse = MutableStateFlow<StolenCarState>(StolenCarState.Empty)
    var stolenCarDetailsResponse = _stolenCarDetailsResponse.asStateFlow()
    private val _stolenCarDetailsApi = MutableStateFlow<StolenCarState>(StolenCarState.Empty)
    var stolenCarDetailsApi = _stolenCarDetailsApi.asStateFlow()

    private val database = Firebase.database
    val list = mutableListOf<stolenCarDetails>()
    fun getCarDetails() {
        database.getReference("Stolen Cars").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                snapshot.children.forEach {
                    val data = it.getValue(stolenCarDetails::class.java) as stolenCarDetails
                    Timber.tag("StolenCarDetails").d(data.carNumber)
                    list.add(data)

                }
                _stolenCarDetailsResponse.value = StolenCarState.Success(list)
            }

            override fun onCancelled(error: DatabaseError) {
                _stolenCarDetailsResponse.value = StolenCarState.Error
            }

        })
    }

    fun getCarDetailsApi() {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                _stolenCarDetailsApi.value = StolenCarState.Loading
                Timber.tag("Stolen Car ").d("Hiii")
                val response = authService.getCars()
                if (response.isSuccessful) {
                    response.body().let {
                        if (it != null) {
                            _stolenCarDetailsApi.value = StolenCarState.Success(it)
                        }else{
                            Timber.tag("Stolen Car ").d("empty")
                        }
                    }
                } else {
                    _stolenCarDetailsApi.value = StolenCarState.Error
                }
            } catch (e: Exception) {
                Timber.tag("Stolen Car").d(e)
                _stolenCarDetailsApi.value = StolenCarState.Error
            }
        }
    }
}


sealed class StolenCarState {
    object Empty : StolenCarState()
    object Loading : StolenCarState()
    class Success(var policeData: Any) : StolenCarState()
    object Error : StolenCarState()
}