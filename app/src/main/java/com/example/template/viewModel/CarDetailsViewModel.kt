package com.example.template.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.data.repository.AuthService
import com.example.template.model.CarDetailsResponse
import com.example.template.utils.DispatcherProvider
import com.example.template.utils.constants.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CarDetailsViewModel @Inject constructor(
    private val authService: AuthService,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _getDetails = MutableStateFlow<CarsDetailsState>(CarsDetailsState.Empty)
    val getDetails = _getDetails.asStateFlow()
    private val _stolenCarDetailsApi = MutableStateFlow<StolenCarState>(StolenCarState.Empty)
    var stolenCarDetailsApi = _stolenCarDetailsApi.asStateFlow()
    private val _getDetailsNew= MutableStateFlow<CarsDetailsState>(CarsDetailsState.Empty)
    var getDetailsNew=_getDetailsNew.asStateFlow()

    val imageUrl: MutableState<String?> = mutableStateOf("")
    val imageUrlAll: MutableState<String?> = mutableStateOf("")
    val list= listOf<String>(
        "63defe2d66a8317e31aaf8ae",
        "63de928d600b5c42a7f07f85",
        "63de1da090dabecdd80557cc",
        "63de1d8f90dabecdd80557ca",
        "63de1d7b90dabecdd80557c8",
        "63de1d5b90dabecdd80557c6"
    )
    fun getCarDetails() {
        viewModelScope.launch(dispatcherProvider.io) {
            _getDetails.value = CarsDetailsState.Loading
            try {
                val response = authService.getCar(Constants.carNumber.ifEmpty { "63defe2d66a8317e31aaf8ae" })
                if (response.isSuccessful) {
                    response.body().let {
                        if (it != null) {
                            _getDetails.value = CarsDetailsState.Success(it)
                            imageUrl.value = getImageUrl(it)
                            Timber.tag("CarDetailsViewModel").d(it.data.carDetails.carModel)
                        }

                    }
                } else {

                    Timber.tag("CarDetailsViewModel").d(response.errorBody()?.string())
                    _getDetails.value = CarsDetailsState.Error
                }
            } catch (e: Exception) {
                Timber.tag("CarDetailsViewModel").d(e)
            }

        }
    }

    fun getCarDetailsNew(carNumber: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            _getDetailsNew.value = CarsDetailsState.Loading
            try {
                val response = authService.getCar(carNumber)
                if (response.isSuccessful) {
                    response.body().let {
                        if (it != null) {
                            _getDetailsNew.value = CarsDetailsState.Success(it)
                            imageUrl.value = getImageUrl(it)
                            Timber.tag("CarDetailsViewModel").d(it.data.carDetails.carModel)
                        }

                    }
                } else {

                    Timber.tag("CarDetailsViewModel").d(response.errorBody()?.string())
                    _getDetailsNew.value = CarsDetailsState.Error
                }
            } catch (e: Exception) {
                Timber.tag("CarDetailsViewModel").d(e)
            }

        }
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

val list = mapOf<String, String>(
    "Maruti Swift" to "https://i.postimg.cc/sf9VG0Hk/swift-Image.jpg",
    "Honda City " to "https://i.postimg.cc/fT9FG4cp/hondacity.jpg",
    "Hyundai Grand i10" to "https://i.postimg.cc/qRDST4ZV/i10.jpg",
    "Mahindra Scorpio" to "https://i.postimg.cc/2yPt1TD0/scorpio1.jpg",
    "Tata Tiago" to "https://i.postimg.cc/PJLcf9BL/tiago.jpg",
    "Toyota Innova" to "https://i.postimg.cc/1RT27RqD/toyota.jpg"
)

fun getImageUrl(carDetailsResponse: CarDetailsResponse): String? {
    return list[carDetailsResponse.data.carDetails.carModel]

}

sealed class CarsDetailsState {
    object Empty : CarsDetailsState()
    object Loading : CarsDetailsState()
    class Success(var policeData: Any) : CarsDetailsState()
    object Error : CarsDetailsState()
}