package com.example.template.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.example.template.model.CarDetailsResponse
import com.example.template.model.CarResponce
import com.example.template.viewModel.CarDetailsViewModel
import com.example.template.viewModel.CarsDetailsState
import com.example.template.viewModel.StolenCarState

@Composable
fun TrackingDetailsScreen(paddingValues: PaddingValues) {

    val viewModel = hiltViewModel<CarDetailsViewModel>()
    LaunchedEffect(key1 = Unit) {
        viewModel.getCarDetails()
        viewModel.getCarDetailsApi()
    }
    val data = viewModel.getDetails.collectAsStateWithLifecycle().value
    val dataStolenCar= viewModel.stolenCarDetailsApi.collectAsStateWithLifecycle().value
    val imageUrl = viewModel.imageUrl.value
    Column(
        Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        when (data) {
            CarsDetailsState.Empty -> {}
            CarsDetailsState.Error -> {}
            CarsDetailsState.Loading -> {}
            is CarsDetailsState.Success -> {
                val carDetails = data.policeData as CarDetailsResponse
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillBounds,
                        model = imageUrl,
                        loading = {
                            Box(modifier = Modifier.height(200.dp)) {
                                CircularProgressIndicator(Modifier.align(Alignment.Center))
                            }

                        },
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    CarTrackingDetails(carDetails = carDetails)
                    Text(
                        text = "Tracking History",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    when (dataStolenCar) {
                        StolenCarState.Empty -> {}
                        StolenCarState.Error -> {}
                        StolenCarState.Loading -> {}
                        is StolenCarState.Success -> {
                            val  carResponce = dataStolenCar.policeData as CarResponce

                            LazyColumn(
                                content = {
                                    items(carResponce.data) {
                                        CarTrackingDetailsList(it.missingDetails)
                                    }
                                })
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CarTrackingDetailsList(policeData: CarResponce.Data.MissingDetails) {
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        modifier = Modifier
            .padding(5.dp)
            .wrapContentHeight()
            .fillMaxWidth(),

        elevation = CardDefaults.cardElevation(4.dp),
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),) {
                Text(
                    text = "Spotted car at 📌 ${policeData.place} ${getData(policeData.time)} registered by ${policeData.policeStation} ", style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }

        }
    )

}


@Composable
fun CarTrackingDetails(carDetails: CarDetailsResponse) {
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        modifier = Modifier
            .padding(5.dp)
            .height(120.dp)
            .fillMaxWidth(),

        elevation = CardDefaults.cardElevation(4.dp),
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
            ) {
                Row(modifier = Modifier.wrapContentHeight(), verticalAlignment = Alignment.Top) {
                    Text(
                        text = carDetails.data.carDetails.carModel,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "- ${carDetails.data.carDetails.color}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = carDetails.data.carDetails.carNumber,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Spotted car at 📌 ${carDetails.data.missingDetails.place} ${getData(carDetails.data.missingDetails.time)} registered by ${carDetails.data.missingDetails.policeStation} ",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }
    )
}
