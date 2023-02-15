package com.example.template.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.data.repository.AuthService
import com.example.template.model.MissingCarRequest
import com.example.template.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MissingDiaryViewModel @Inject constructor(
    private val authService: AuthService,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    fun saveData(missingCarRequest: MissingCarRequest) {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                val response = authService.saveCar(missingCarRequest)
                if (response.isSuccessful) {
                    response.body().let {
                        val resp = it
                        if (resp != null) {
                            Timber.tag("MissingDiaryViewModel").d(resp.car.missingDetails.carNumber)
                        }
                    }
                } else {
                    Timber.tag("MissingDiaryViewModelError").d(response.errorBody().toString())
                }
            } catch (e: Exception) {
                Timber.tag("MissingDiaryViewModelError").d(e)
            }
        }

    }

}
